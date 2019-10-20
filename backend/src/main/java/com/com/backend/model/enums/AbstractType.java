package com.com.backend.model.enums;

public enum AbstractType {

    C("Case"),
    R("Research");

    private String type;

    AbstractType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }



}
