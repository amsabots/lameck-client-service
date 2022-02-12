package com.amsabots.jenzi.client_service.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class MQRabbitConfig {
    @Bean
    public Queue fundiQueue() {
        return QueueBuilder.durable(MQParamsConstants.FUNDI_NEW_PROJECT_QUEUE).build();
    }

    @Bean
    public Exchange appExchange() {
        return ExchangeBuilder.directExchange(MQParamsConstants.JENZI_EXCHANGE).durable(true).build();
    }

    @Bean
    public Binding fundiProjectBinder() {
        return BindingBuilder.bind(fundiQueue()).to(appExchange()).with(MQParamsConstants.FUNDI_NEW_PROJECT_QUEUE_KEY).noargs();
    }
}
