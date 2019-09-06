package com.com.backend.exception;

import com.com.backend.Util;
import com.com.backend.domain.enums.Errors;

public class AppException extends Exception {

    private String error;
    private String message;

    public AppException(Errors error) {
        this.error = error.name();
        this.message = error.getMessage();
    }

    public AppException(Errors error, Object... msgParams) {
        super();
        this.error = error.name();
        this.message = Util.replace(error.getMessage(), msgParams);
    }

    public String getError() {
        return error;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
