package org.ht.model.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

/**
 * 用户视频点赞实体类
 * 
 * @author HotTube
 * @since 2024-01-01
 */
@TableName(value ="user_video_like")
@Data
@Builder
public class UserVideoLike {
    
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
     * 点赞状态：1=已点赞，0=未点赞
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