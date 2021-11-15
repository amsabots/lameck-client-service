package com.amsabots.jenzi.client_service.errorHandlers;


import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.RollbackException;

@ControllerAdvice
public class ErrorAdvisorController {

    @ExceptionHandler({CustomBadRequest.class, HttpMessageNotReadableException.class})
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

    @ExceptionHandler({RollbackException.class, EmptyResultDataAccessException.class})
    public ResponseEntity<ErrorEntity> handleJpaSqlException(Exception ex) {
        if (ex.getMessage().contains("exists"))
            return ResponseEntity.ok(new ErrorEntity("Resource with the id provided cannot be found.",
                    HttpStatus.NOT_FOUND.value(), "SQL_EXCEPTION"));
        return ResponseEntity.ok(new ErrorEntity(ex.getMessage(),
                HttpStatus.UNPROCESSABLE_ENTITY.value(), "SQL_EXCEPTION"));
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorEntity> handleMissingParameters(Exception ex) {
        return ResponseEntity.ok(new ErrorEntity(ex.getMessage(), HttpStatus.BAD_REQUEST.value(), "BAD_REQUEST"));
    }
}
