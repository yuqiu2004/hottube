package org.ht.model.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 用户视频评论实体类
 * 
 * @author HotTube
 * @since 2024-01-01
 */
@TableName(value ="user_video_comment")
@Data
public class UserVideoComment {
    
    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 视频ID
     */
    private Long videoId;

    /**
     * 父评论ID，用于回复功能
     */
    private Long parentId;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}