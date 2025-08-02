package org.ht.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    public static final String VIDEO_PUBLISH_QUEUE_NAME = "video.publish.queue";
    public static final String VIDEO_PUBLISH_EXCHANGE_NAME = "video.publish.exchange";
    public static final String VIDEO_PUBLISH_ROUTING_KEY = "video.publish.key";

    @Bean
    public Queue queue() {
        return new Queue(VIDEO_PUBLISH_QUEUE_NAME, true); // 持久化队列
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(VIDEO_PUBLISH_EXCHANGE_NAME);
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(exchange()).with(VIDEO_PUBLISH_ROUTING_KEY);
    }
}

