package com.com.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(AppException.class)
    public ResponseEntity<ExceptionResponse> exceptionHandler(HttpServletRequest request, AppException ex){
        ExceptionResponse response = new ExceptionResponse();
        response.setMessage(ex.getMessage());
        response.setError(ex.getError());
        response.setTimestamp(LocalDateTime.now());
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setPath(request.getServletPath());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AbstractNotFoundException.class)
    public ResponseEntity<ExceptionResponse> exceptionHandler(HttpServletRequest request, AbstractNotFoundException ex){
        ExceptionResponse response = new ExceptionResponse();
        response.setMessage(ex.getMessage());
        response.setError(ex.getError());
        response.setTimestamp(LocalDateTime.now());
        response.setStatus(HttpStatus.NOT_FOUND.value());
        response.setPath(request.getServletPath());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}
