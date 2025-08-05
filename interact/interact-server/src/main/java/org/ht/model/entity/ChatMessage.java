package org.ht.model.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 聊天消息实体类
 * 
 * @author HotTube
 * @since 2024-01-01
 */
@TableName(value ="chat_message")
@Data
public class ChatMessage {
    
    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 发送者ID
     */
    private Integer senderId;

    /**
     * 接收者ID
     */
    private Integer receiverId;

    /**
     * 消息内容
     */
    private String content;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}