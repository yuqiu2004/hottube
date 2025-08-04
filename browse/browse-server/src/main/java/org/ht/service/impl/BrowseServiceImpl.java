package org.ht.service.impl;

import org.apache.dubbo.config.annotation.DubboReference;
import org.ht.constant.RedisKey;
import org.ht.mapper.VideoMetadataMapper;
import org.ht.model.context.ContextData;
import org.ht.model.dto.UserInfo;
import org.ht.model.entity.VideoMetadata;
import org.ht.model.mongo.VideoRecord;
import org.ht.model.mongo.WatchRecord;
import org.ht.model.response.BrowseRandomResponse;
import org.ht.model.response.UserInfoResponse;
import org.ht.model.vo.VideoVo;
import org.ht.repository.WatchRecordRepository;
import org.ht.service.BrowseService;
import org.ht.service.UserRpcService;
import org.ht.util.RedisUtil;
import org.ht.util.TimeUtil;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BrowseServiceImpl implements BrowseService {

    @Resource
    private RedisUtil redisUtil;

    @Resource
    private MongoTemplate mongoTemplate;

    @Resource
    private VideoMetadataMapper videoMetadataMapper;

    @Resource
    private WatchRecordRepository watchRecordRepository;

    @DubboReference
    private UserRpcService userRpcService;

    @Override
    public BrowseRandomResponse random() {
        UserInfo userInfo = ContextData.getUserInfo();
        // 先从缓存获取已经推荐的视频
        String recommendedKey = RedisKey.getVideoRecommendedKey(userInfo.getUid());
        Set<Integer> rec = redisUtil.getSet(recommendedKey).stream()
                .filter(Integer.class::isInstance)
                .map(Integer.class::cast)
                .collect(Collectors.toSet());
        // 从mongo中去重搜索
        List<VideoRecord> list = getNotRecAndNotWatchVideo(rec, userInfo.getUid());
        // 插入redis
        redisUtil.addSetValues(recommendedKey, list.stream().map(VideoRecord::getVideoId).collect(Collectors.toList()).toArray());
        redisUtil.expire(recommendedKey, TimeUtil.getTodayRemainSeconds());
        // 组装返回
        List<VideoVo> videoVoList = list.parallelStream().map(videoRecord -> {
            VideoMetadata videoMetadata = videoMetadataMapper.selectById(videoRecord.getVideoId());
            UserInfoResponse info = userRpcService.getUserInfo(videoRecord.getCreatorId());
            return VideoVo.builder()
                    .id(videoMetadata.getId())
                    .title(videoMetadata.getTitle())
                    .description(videoMetadata.getDescription())
                    .coverUrl(videoMetadata.getCoverUrl())
                    .playCount(videoMetadata.getPlayCount())
                    .durationSeconds(videoMetadata.getDurationSeconds())
                    .creatorId(info.getUserVo().getUid())
                    .nickname(info.getUserVo().getNickname())
                    .build();
        }).collect(Collectors.toList());
        return BrowseRandomResponse.builder().videos(videoVoList).build();
    }

    @Override
    public void recordWatched(Long videoId) {
        int uid = ContextData.getUserInfo().getUid();
        Query query = new Query(Criteria.where("userId").is(uid));
        Update update = new Update().addToSet("videoIds", videoId);
        mongoTemplate.updateFirst(query, update, WatchRecord.class);
    }

    public List<VideoRecord> getNotRecAndNotWatchVideo(Set<Integer> rec, Integer uid) {
        Aggregation agg = Aggregation.newAggregation(
                // 1. 左表 video_record，右表 watch_record，左videoId，右videoIds
                Aggregation.lookup("watch_record", "videoId", "videoIds", "watchRecords"),
                // 2. 过滤 watchRecords 中包含 userId == uid 的文档
                Aggregation.match(Criteria.where("watchRecords").not().elemMatch(Criteria.where("userId").is(uid))),
                // 3. 排除 Redis 已推荐过的视频
                Aggregation.match(Criteria.where("videoId").nin(rec)),
                // 4. 随机抽样
                Aggregation.sample(20)
        );
        List<VideoRecord> videoRecord = mongoTemplate.aggregate(agg, "video_record", VideoRecord.class).getMappedResults();
        // 检查是否有20条 没有需要再查一次
        if (videoRecord.size() < 20) {
            Set<Long> collect = videoRecord.stream().map(VideoRecord::getVideoId).collect(Collectors.toSet());
            agg = Aggregation.newAggregation(
                    Aggregation.match(Criteria.where("videoId").nin(collect)),
                    Aggregation.sample(20 - videoRecord.size())
            );
            videoRecord.addAll(mongoTemplate.aggregate(agg, "video_record", VideoRecord.class).getMappedResults());
        }
        return videoRecord;
    }

}
