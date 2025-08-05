package org.ht.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.ht.model.entity.ChatMessage;
import org.ht.model.context.ContextData;
import org.ht.model.response.MessageResponse;
import org.ht.service.ChatMessageService;
import org.ht.mapper.ChatMessageMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 聊天消息Service实现类
 * 
 * @author HotTube
 * @since 2024-01-01
 */
@Service
public class ChatMessageServiceImpl implements ChatMessageService{

    @Resource
    private ChatMessageMapper chatMessageMapper;

    @Override
    public MessageResponse getHistoryMessages(Integer fid, int current, int size) {
        int uid = ContextData.getUserInfo().getUid();
        Page<ChatMessage> page = new Page(current-1, size);
        Map<String, Integer> map1 = new HashMap<>(), map2 = new HashMap<>();
        map1.put("sender_id", uid);
        map1.put("receiver_id", fid);
        map2.put("sender_id", fid);
        map2.put("receiver_id", uid);
        QueryWrapper query = new QueryWrapper<>().allEq(map1).allEq(map2).orderByDesc("create_time");
        Page<ChatMessage> result = chatMessageMapper.selectPage(page, query);
        return MessageResponse.builder()
                .total((int) result.getTotal())
                .messageList(result.getRecords())
                .build();
    }
}
