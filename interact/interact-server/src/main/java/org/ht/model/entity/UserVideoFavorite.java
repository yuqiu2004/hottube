package org.ht.model.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

/**
 * 用户视频收藏实体类
 * 
 * @author HotTube
 * @since 2024-01-01
 */
@TableName(value ="user_video_favorite")
@Data
@Builder
public class UserVideoFavorite {
    
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
     * 收藏状态：1=已收藏，0=未收藏
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