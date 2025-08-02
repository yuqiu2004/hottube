package org.ht.model.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName(value ="video_metadata")
@Data
public class VideoMetadata {

    @TableId
    private Long id;

    private String title;

    private String description;

    private String coverUrl;

    private String originalUrl;

    private Integer originalBitrate;

    private Integer originalExists;

    private String p720Url;

    private Integer p720Bitrate;

    private Integer p720Exists;

    private String p360Url;

    private Integer p360Bitrate;

    private Integer p360Exists;

    private Integer durationSeconds;

    private Long playCount;

    private Long creatorId;

    /**
     * 1=发布, 0=待审, 2=下架
     */
    private Integer status;

    private Date createTime;

    private Date updateTime;
}