package com.com.backend.domain.enums;

public enum Title {

    Student("Student"),
    PhD_Student("PhD Student");

    private String message;


    Title(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
