package com.amsabots.jenzi.client_service.errorHandlers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@ToString
public class ErrorEntity {
    private String message;
    private int statusCode;
    private String errorCode;

    public ErrorEntity(String message, int statusCode, String errorCode) {
        this.message = message;
        this.statusCode = statusCode;
        this.errorCode = errorCode;
    }

    private LocalDateTime time = LocalDateTime.now();
}
