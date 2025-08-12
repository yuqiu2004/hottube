package org.ht.mq;

import org.ht.constant.MQConst;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class VideoPublishListener {

    @RabbitListener(queues = MQConst.VIDEO_PUBLISH_QUEUE_NAME)
    public void receiveMessage(String message) {
        System.out.println("收到消息: " + message);
        // 这里写你接收到消息后的业务逻辑
    }

}
