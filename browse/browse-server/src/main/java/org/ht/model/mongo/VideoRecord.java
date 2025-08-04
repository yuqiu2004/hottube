package org.ht.model.mongo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "video_record")
@Data
public class VideoRecord {

    @Id
    private String id;

    private Long videoId;

    private Integer creatorId;
}
