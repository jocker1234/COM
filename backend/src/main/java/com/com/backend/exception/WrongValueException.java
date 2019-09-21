package com.com.backend.exception;

import com.com.backend.model.enums.EntityType;
import com.com.backend.model.enums.ExceptionType;
import com.com.backend.model.enums.Fields;

public class WrongValueException extends AppException {

    public WrongValueException(ExceptionType error) {
        super(error);
    }

    public WrongValueException(ExceptionType error, Fields fields) {
        super();
        this.error = error.name();
        this.message = error.getMessage();
        this.parameters = new Object[]{fields.getField()};
    }

    public WrongValueException(ExceptionType error, String... msgParams) {
        super(error, msgParams);
    }

    public WrongValueException(EntityType entityType, ExceptionType exceptionType) {
        super(entityType, exceptionType);
    }

    public WrongValueException(EntityType entityType, ExceptionType exceptionType, String... msgParams) {
        super(entityType, exceptionType, msgParams);
    }
}
