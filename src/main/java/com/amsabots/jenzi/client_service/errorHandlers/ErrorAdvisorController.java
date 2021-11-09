package com.amsabots.jenzi.client_service.errorHandlers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorAdvisorController {

    @ExceptionHandler(CustomBadRequest.class)
    public ResponseEntity<ErrorEntity> handleBadRequest(Exception ex) {
        return ResponseEntity.ok(new ErrorEntity(ex.getMessage(), HttpStatus.BAD_REQUEST.value(), "ERRBADREQUEST"));
    }

    @ExceptionHandler(CustomInternalServerError.class)
    public ResponseEntity<ErrorEntity> handleInterServerError(Exception ex) {
        return ResponseEntity.ok(new ErrorEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), "SERVERERR"));
    }

    @ExceptionHandler(CustomResourceNotFound.class)
    public ResponseEntity<ErrorEntity> handleResourceNotFound(Exception ex) {
        return ResponseEntity.ok(new ErrorEntity(ex.getMessage(), HttpStatus.NOT_FOUND.value(), "NOT_FOUND"));
    }
}
