package com.com.backend.model.enums;

public enum ExceptionType {

    EMAIL_EXIST("email.exist"),
    EMAIL_FORMAT("email.format"),
    PASSPORT_NUMBER_FORMAT("passport_number.format"),
    NEED_VISA("need_visa"),
/*
    ENTITY_NOT_FOUND("not.found"),
    USER_NOT_FOUND("user.not.found"),
    COUNTRY_NOT_FOUND("country.not.found"),
    GENDER_NOT_FOUND("gender.not.found"),
    TITLE_NOT_FOUND("title.not.found"),
    YEAR_OF_STUDY_NOT_FOUND("year_of_study.not.found"),
*/
    PRIVILEGES("privileges"),

    ABSTRACT_SENT("abstract.sent"),

    WRONG_STATUS("wrong.status"),
    NOT_FOUND("not.found"),
    WRONG_VALUE("wrong.value"),
    NO_ACCESS("no.access"),

    ABSTRACT_AMMOUNT("abstract.amount");

    private String message;

    ExceptionType(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
