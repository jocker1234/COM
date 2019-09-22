package com.com.backend.exception;

import com.com.backend.model.enums.EntityType;
import com.com.backend.model.enums.ExceptionType;
import org.springframework.beans.factory.annotation.Configurable;

@Configurable
public class AppException extends Exception {

    protected String error;
    protected String message;
    protected Object[] parameters;

    public AppException() {
    }

    public AppException(ExceptionType error) {
        super();
        this.error = error.name();
        this.message = error.getMessage();
    }

    public AppException(ExceptionType error, Object... msgParams) {
        super();
        this.error = error.name();
        this.message = error.getMessage();
        this.parameters = msgParams;
    }

    public AppException(EntityType entityType, ExceptionType exceptionType) {
        super();
        this.error = exceptionType.name();
        String message = getMessageTemplate(entityType, exceptionType);
        this.message = message;
    }

    public AppException(EntityType entityType, ExceptionType exceptionType, String... msgParams) {
        super();
        this.error = exceptionType.name();
        String message = getMessageTemplate(entityType, exceptionType);
        this.message = message;
        this.parameters = msgParams;
    }

    private static String getMessageTemplate(EntityType entityType, ExceptionType exceptionType) {
        return entityType.name().concat(".").concat(exceptionType.getMessage()).toLowerCase();
    }

    public String getError() {
        return error;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Object[] getParameters() {
        return parameters;
    }

}
