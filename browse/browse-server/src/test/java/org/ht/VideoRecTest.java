package org.ht;

import org.ht.model.mongo.VideoRecord;
import org.ht.model.mongo.WatchRecord;
import org.ht.service.BrowseService;
import org.ht.service.impl.BrowseServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import javax.annotation.Resource;
import java.util.*;

@SpringBootTest
public class VideoRecTest {

    @Resource
    private MongoTemplate mongoTemplate;

    @Resource
    private BrowseServiceImpl browseServiceImpl;

    @Test
    public void insertMockData() {
        // 插入视频数据
        List<VideoRecord> videoList = new ArrayList<>();
        for (int i = 1; i <= 50; i++) {
            VideoRecord video = new VideoRecord();
            video.setVideoId(i);
            video.setTitle("视频标题 " + i);
            video.setDescription("描述 " + i);
            video.setCoverUrl("http://example.com/cover/" + i + ".jpg");
            video.setDuration(60 + i);
            video.setViewCount(new Random().nextInt(1000));
            videoList.add(video);
        }
        mongoTemplate.insertAll(videoList);

        // 插入用户观看记录（假设 uid = 1001）
        WatchRecord record = new WatchRecord();
        record.setUserId(1001);
        List<Integer> watched = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            watched.add(i); // 已观看前 20 个视频
        }
        record.setVideoIds(watched);
        record.setTimestamp(System.currentTimeMillis());
        mongoTemplate.save(record);
    }

    @Test
    public void clearMongodb() {
        mongoTemplate.remove(new Query(), "video_record");
        mongoTemplate.remove(new Query(), "watch_record");
    }

    @Test
    public void testGetNotRecAndNotWatchVideo() {
        // 模拟 Redis 推荐过的 ID
        Set<Integer> recommended = new HashSet<>();
        for (int i = 10; i <= 25; i++) {
            recommended.add(i);
        }
        List<VideoRecord> videos = browseServiceImpl.getNotRecAndNotWatchVideo(recommended, 1001);
        System.out.println("推荐结果数量: " + videos.size());
        for (VideoRecord video : videos) {
            System.out.println(video.getVideoId() + " - " + video.getTitle());
        }
    }
}
