package com.amsabots.jenzi.client_service.errorHandlers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Data
@ToString
@AllArgsConstructor
public class ErrorEntity {
    private String message;
    private int statusCode;
    private String errorCode;
}
