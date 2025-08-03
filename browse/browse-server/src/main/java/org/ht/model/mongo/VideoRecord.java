package org.ht.model.mongo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "video_record")
@Data
public class VideoRecord {

    @Id
    private String id;

    private Integer videoId;

    /**
     * 视频标题
     */
    private String title;

    /**
     * 视频描述
     */
    private String description;

    /**
     * 封面图链接
     */
    private String coverUrl;

    /**
     * 视频时长（秒）
     */
    private int duration;

    /**
     * 视频观看数
     */
    private int viewCount;
}
