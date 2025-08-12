package org.ht.config;

import org.ht.constant.MQConst;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean
    public Queue queue() {
        return new Queue(MQConst.VIDEO_TRANS_QUEUE_NAME, true); // 持久化队列
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(MQConst.VIDEO_TRANS_EXCHANGE_NAME);
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(exchange()).with(MQConst.VIDEO_TRANS_ROUTING_KEY);
    }
}

