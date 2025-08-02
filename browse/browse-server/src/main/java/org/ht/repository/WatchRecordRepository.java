package org.ht.repository;

import org.ht.model.mongo.WatchRecord;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WatchRecordRepository extends MongoRepository<WatchRecord, String> {

}
