package com.com.backend.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.Optional;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(AppException.class)
    public ResponseEntity<ExceptionResponse> exceptionHandler(HttpServletRequest request, AppException ex) {
        ExceptionResponse response = new ExceptionResponse();
        response.setMessage(getMessage(ex));
        response.setError(ex.getError());
        response.setTimestamp(LocalDateTime.now());
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setPath(request.getServletPath());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AbstractNotFoundException.class)
    public ResponseEntity<ExceptionResponse> exceptionHandler(HttpServletRequest request, AbstractNotFoundException ex) {
        ExceptionResponse response = new ExceptionResponse();
        response.setMessage(getMessage(ex));
        response.setError(ex.getError());
        response.setTimestamp(LocalDateTime.now());
        response.setStatus(HttpStatus.NOT_FOUND.value());
        response.setPath(request.getServletPath());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(WrongValueException.class)
    public ResponseEntity<ExceptionResponse> exceptionHandler(HttpServletRequest request, WrongValueException ex) {
        ExceptionResponse response = new ExceptionResponse();
        response.setMessage(getMessage(ex));
        response.setError(ex.getError());
        response.setTimestamp(LocalDateTime.now());
        response.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
        response.setPath(request.getServletPath());

        return new ResponseEntity<>(response, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(AccessException.class)
    public ResponseEntity<ExceptionResponse> exceptionHandler(HttpServletRequest request, AccessException ex) {
        ExceptionResponse response = new ExceptionResponse();
        response.setMessage(getMessage(ex));
        response.setError(ex.getError());
        response.setTimestamp(LocalDateTime.now());
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setPath(request.getServletPath());

        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    private String getMessage(AppException ex) {
        Optional<String> templateContent = Optional.ofNullable(messageSource.getMessage(ex.getMessage(), ex.getParameters(), LocaleContextHolder.getLocale()));
        if (templateContent.isPresent() && ex.parameters != null) {
            return MessageFormat.format(templateContent.get(), ex.getParameters());
        }
        return templateContent.get();
    }

}
