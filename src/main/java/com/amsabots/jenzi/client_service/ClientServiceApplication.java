package com.amsabots.jenzi.client_service;

import com.amsabots.jenzi.client_service.errorHandlers.CustomInternalServerError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;

@SpringBootApplication
@Slf4j
public class ClientServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientServiceApplication.class, args);
    }

}
