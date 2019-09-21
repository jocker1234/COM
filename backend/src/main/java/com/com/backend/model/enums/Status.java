package com.com.backend.model.enums;

public enum Status {

    DO("D"),
    FORWARDED("F"),
    APPROVED("A"),
    REJECTED("R");

    String status;

    Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
