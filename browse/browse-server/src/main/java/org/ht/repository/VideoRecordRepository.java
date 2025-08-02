package org.ht.repository;

import org.ht.model.mongo.VideoRecord;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRecordRepository extends MongoRepository<VideoRecord, String> {
}
