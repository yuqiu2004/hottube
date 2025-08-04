package org.ht.service.impl;

import org.ht.constant.RedisKey;
import org.ht.model.context.ContextData;
import org.ht.model.dto.UserInfo;
import org.ht.model.mongo.VideoRecord;
import org.ht.model.response.BrowseRandomResponse;
import org.ht.repository.VideoRecordRepository;
import org.ht.service.BrowseService;
import org.ht.util.RedisUtil;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
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
    private VideoRecordRepository videoRecordRepository;

    @Resource
    private MongoTemplate mongoTemplate;

    @Override
    public BrowseRandomResponse random() {
        UserInfo userInfo = ContextData.getUserInfo();
        // 先从缓存获取已经推荐的视频
        Set<Integer> rec = redisUtil.getSet(RedisKey.getVideoRecommendedKey(userInfo.getUid())).stream()
                .filter(Integer.class::isInstance)
                .map(Integer.class::cast)
                .collect(Collectors.toSet());
        // 从mongo中去重搜索
        List<VideoRecord> list = getNotRecAndNotWatchVideo(rec, userInfo.getUid());
        // 组装返回
        return null;
    }

    private List<VideoRecord> getNotRecAndNotWatchVideo(Set<Integer> rec, Integer uid) {
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
        return mongoTemplate.aggregate(agg, "video_record", VideoRecord.class).getMappedResults();
    }

}
