package com.amsabots.jenzi.client_service;

import com.amsabots.jenzi.client_service.config.MQParamsConstants;
import com.amsabots.jenzi.client_service.errorHandlers.CustomInternalServerError;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;

@SpringBootApplication
@Slf4j
@EnableEurekaClient
@EnableRabbit
public class ClientServiceApplication {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private ObjectMapper mapper;

    public static void main(String[] args) {
        SpringApplication.run(ClientServiceApplication.class, args);
    }

}
