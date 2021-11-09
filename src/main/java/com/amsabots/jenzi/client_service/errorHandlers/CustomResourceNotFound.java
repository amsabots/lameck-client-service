package com.amsabots.jenzi.client_service.errorHandlers;

public class CustomResourceNotFound extends RuntimeException{
    public CustomResourceNotFound(String message) {
        super(message);
    }
}
