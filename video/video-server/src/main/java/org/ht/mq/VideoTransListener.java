package org.ht.mq;

import cn.hutool.json.JSONUtil;
import org.ht.constant.MQConst;
import org.ht.model.dto.VideoTransDTO;
import org.ht.util.VideoUtil;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class VideoTransListener {

    @Resource
    private VideoUtil videoUtil;

    @RabbitListener(queues = MQConst.VIDEO_TRANS_QUEUE_NAME)
    public void receiveMessage(String message) {
        try {
            VideoTransDTO transDTO = JSONUtil.toBean(message, VideoTransDTO.class);
            // 处理视频转码

            // 视频上传

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

}
