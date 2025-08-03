package org.ht.model.mongo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "watch_record")
@Data
public class WatchRecord {

    @Id
    private String id;

    private Integer userId;

    private Integer videoId;

    private long timestamp;

}
