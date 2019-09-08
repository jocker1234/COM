package com.com.backend.exception;

import com.com.backend.domain.enums.Errors;

public class WrongValueException extends AppException {
    public WrongValueException(Errors message, Object... msgParams) {
        super(message, msgParams);
    }
}
