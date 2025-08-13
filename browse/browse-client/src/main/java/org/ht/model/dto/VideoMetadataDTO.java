package org.ht.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class VideoMetadataDTO {

    /**
     * 视频标题
     */
    private String title;

    /**
     * 视频描述
     */
    private String description;

    /**
     * 封面图片URL
     */
    private String coverUrl;

    /**
     * 原始视频URL
     */
    private String originalUrl;

    /**
     * 原始视频比特率
     */
    private Integer originalBitrate;

    /**
     * 原始视频是否存在：1=存在，0=不存在
     */
    private Integer originalExists;

    /**
     * 720P视频URL
     */
    private String p720Url;

    /**
     * 720P视频比特率
     */
    private Integer p720Bitrate;

    /**
     * 720P视频是否存在：1=存在，0=不存在
     */
    private Integer p720Exists;

    /**
     * 360P视频URL
     */
    private String p360Url;

    /**
     * 360P视频比特率
     */
    private Integer p360Bitrate;

    /**
     * 360P视频是否存在：1=存在，0=不存在
     */
    private Integer p360Exists;

    /**
     * 视频时长（秒）
     */
    private Long durationSeconds;

    /**
     * 创建者ID
     */
    private Integer creatorId;

    /**
     * 分类id
     */
    private List<Long> categoryIds;

}
