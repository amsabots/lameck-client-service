package com.amsabots.jenzi.client_service.errorHandlers;

public class CustomBadRequest extends RuntimeException{
    public CustomBadRequest(String message) {
        super(message);
    }
}
