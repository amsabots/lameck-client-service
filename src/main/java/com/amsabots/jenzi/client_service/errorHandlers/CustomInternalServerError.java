package com.amsabots.jenzi.client_service.errorHandlers;

public class CustomInternalServerError extends RuntimeException{
    public CustomInternalServerError(String message) {
        super(message);
    }
}
