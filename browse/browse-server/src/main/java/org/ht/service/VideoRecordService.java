package org.ht.service;

import org.ht.model.mongo.VideoRecord;
import org.ht.repository.VideoRecordRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

@Service
public class VideoRecordService {

    @Resource
    private VideoRecordRepository videoRecordRepository;

    @Resource
    private MongoTemplate mongoTemplate;

    public List<VideoRecord> findNotRepeatVideos(Set<String> excludedIds, String watchRecordId) {
        // 创建查询条件
        Query query = new Query();
        query.addCriteria(Criteria.where("id").nin(excludedIds)); // 排除指定的 ID 集合
        query.addCriteria(Criteria.where("id").ne(watchRecordId)); // 排除指定的 watchRecordId

        // 执行查询
        return mongoTemplate.find(query, VideoRecord.class);
    }
}
