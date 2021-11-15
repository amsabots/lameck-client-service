package com.amsabots.jenzi.client_service;

import com.amsabots.jenzi.client_service.entities.ClientSettings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
@Slf4j
public class ClientServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientServiceApplication.class, args);
    }



    @PostConstruct
    public void checkClientDefaults(){
        ClientSettings clientSettings = new ClientSettings();
        log.info(clientSettings.toString());
    }
}
