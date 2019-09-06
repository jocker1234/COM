package com.com.backend.domain.enums;

public enum Errors {

    EMAIL_EXIST("Email exist in database."),
    EMAIL_FORMAT("Wrong email format"),
    PASSPORT_NUMBER_FORMAT("Wrong passport number format"),
    NEED_VISA("You don't need Visa"),
    USER_NOT_FOUND("User not found"),
    COUNTRY_NOT_FOUND("Country not found"),
    GENDER_NOT_FOUND("Gender not found"),
    TITLE_NOT_FOUND("Title not found"),
    YEAR_OF_STUDY_NOT_FOUND("Year of study not found"),

    ABSTRACT_SENT("The work has already been sent"),

    WRONG_STATUS("Abstract has already been transferred"),
    NOT_FOUND("Entity not found"),
    WRONG_VALUE("{0} field cannot be empty");

    private String message;

    Errors(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
