package com.acropolis.apiround.controller;

import com.acropolis.apiround.config.BfhlProperties;
import com.acropolis.apiround.dto.BfhlResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final BfhlProperties properties;

    public GlobalExceptionHandler(BfhlProperties properties) {
        this.properties = properties;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BfhlResponse> handleValidationException() {
        return badRequest();
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<BfhlResponse> handleInvalidJson() {
        return badRequest();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BfhlResponse> handleUnexpectedException() {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(failureResponse());
    }

    private BfhlResponse failureResponse() {
        return new BfhlResponse(
                false,
                properties.userId(),
                properties.email(),
                properties.rollNumber(),
                List.of(),
                List.of(),
                List.of(),
                List.of(),
                "0",
                ""
        );
    }

    private ResponseEntity<BfhlResponse> badRequest() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(failureResponse());
    }
}
