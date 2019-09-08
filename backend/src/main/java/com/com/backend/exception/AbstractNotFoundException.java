package com.com.backend.exception;

import com.com.backend.domain.enums.Errors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class AbstractNotFoundException extends AppException {

    public AbstractNotFoundException(Errors error) {
        super(error);
    }

    public AbstractNotFoundException(Errors error, Object... msgParams) {
        super(error, msgParams);
    }
}
