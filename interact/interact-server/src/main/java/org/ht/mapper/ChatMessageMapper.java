package org.ht.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.ht.model.entity.ChatMessage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 聊天消息Mapper接口
 * 
 * @author HotTube
 * @since 2024-01-01
 */
@Mapper
public interface ChatMessageMapper extends BaseMapper<ChatMessage> {

}
