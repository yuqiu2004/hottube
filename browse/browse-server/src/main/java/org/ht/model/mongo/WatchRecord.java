package org.ht.model.mongo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "watch_record")
@Data
public class WatchRecord {

    @Id
    private String id;

    private String userId;

    private String videoId;

    private long timestamp;

}
