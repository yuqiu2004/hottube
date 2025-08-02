package org.ht.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.ht.entity.ChatMessage;
import org.ht.service.ChatMessageService;
import org.ht.mapper.ChatMessageMapper;
import org.springframework.stereotype.Service;

/**
 * 聊天消息Service实现类
 * 
 * @author HotTube
 * @since 2024-01-01
 */
@Service
public class ChatMessageServiceImpl extends ServiceImpl<ChatMessageMapper, ChatMessage>
implements ChatMessageService{

}
