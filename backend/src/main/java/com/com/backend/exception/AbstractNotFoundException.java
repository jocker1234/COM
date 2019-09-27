package com.com.backend.exception;

import com.com.backend.model.enums.EntityType;
import com.com.backend.model.enums.ExceptionType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Arrays;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class AbstractNotFoundException extends AppException {

    private static final long serialVersionUID = 1L;

    public AbstractNotFoundException(ExceptionType error) {
        super(error);
    }

    public AbstractNotFoundException(ExceptionType error, String... msgParams) {
        super(error, Arrays.asList(msgParams));
    }

    public AbstractNotFoundException(EntityType entityType, ExceptionType exceptionType) {
        super(entityType, exceptionType);
    }

    public AbstractNotFoundException(EntityType entityType, ExceptionType exceptionType, String... msgParams) {
        super(entityType, exceptionType, msgParams);
    }
}
