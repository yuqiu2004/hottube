package org.ht.model.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 视频元数据实体类
 * 
 * @author HotTube
 * @since 2024-01-01
 */
@TableName(value ="video_metadata")
@Data
public class VideoMetadata {

    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

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
     * 播放次数
     */
    private Long playCount;

    /**
     * 创建者ID
     */
    private Long creatorId;

    /**
     * 视频状态：1=发布, 0=待审, 2=下架
     */
    private Integer status;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}