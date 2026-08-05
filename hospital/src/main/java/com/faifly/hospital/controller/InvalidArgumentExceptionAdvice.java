package com.faifly.hospital.controller;

import com.faifly.hospital.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackageClasses = HospitalController.class)
public class InvalidArgumentExceptionAdvice {

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleInvalidArgumentXException(Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(e.getMessage()));
    }
}