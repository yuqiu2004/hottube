package org.ht.config;

import org.ht.constant.MQConst;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean
    public Queue queue() {
        return new Queue(MQConst.VIDEO_PUBLISH_QUEUE_NAME, true); // 持久化队列
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(MQConst.VIDEO_PUBLISH_EXCHANGE_NAME);
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(exchange()).with(MQConst.VIDEO_PUBLISH_ROUTING_KEY);
    }
}

