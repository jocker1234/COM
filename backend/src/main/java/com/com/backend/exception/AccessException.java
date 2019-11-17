package com.com.backend.exception;

import com.com.backend.model.enums.ExceptionType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class AccessException extends AppException {

    public AccessException(ExceptionType error) {
        super(error);
    }
}
