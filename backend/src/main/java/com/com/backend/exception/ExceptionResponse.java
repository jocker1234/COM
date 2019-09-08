package com.com.backend.exception;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ExceptionResponse {

    private String error;
    private Integer status;
    private String path;
    private String message;
    private LocalDateTime timestamp;

    public ExceptionResponse() { }

}
