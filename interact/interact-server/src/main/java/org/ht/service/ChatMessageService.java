package org.ht.service;

import org.ht.model.response.MessageResponse;

/**
 * 聊天消息Service接口
 * 
 * @author HotTube
 * @since 2024-01-01
 */
public interface ChatMessageService {

    MessageResponse getHistoryMessages(Integer fid, int current, int size);
}
