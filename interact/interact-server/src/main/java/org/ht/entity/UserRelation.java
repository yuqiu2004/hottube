package org.ht.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 用户关系实体类
 * 
 * @author HotTube
 * @since 2024-01-01
 */
@TableName(value ="user_relation")
@Data
public class UserRelation {
    
    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 关注者ID
     */
    private Long followerId;

    /**
     * 被关注者ID
     */
    private Long followeeId;

    /**
     * 关系类型：1=关注，2=好友
     */
    private Integer relationType;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}