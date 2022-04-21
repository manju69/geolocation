package com.project.geolocation.controller;

import com.project.geolocation.domain.dto.ApiResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

@Log4j2
@RestControllerAdvice
public class ErrorHandler{

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleValidationExceptions(ConstraintViolationException ex) {
        log.warn("Invalid input");
        ApiResponse<Object, Object> response = ApiResponse.failed(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        return ResponseEntity.ok(response);
    }
}
