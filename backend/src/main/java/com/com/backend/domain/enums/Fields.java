package com.com.backend.domain.enums;

public enum Fields {

    //Abstract
    CATEGORY("Category"),
    AUTHOR("Author"),
    TITLE("Title"),
    TUTORS("Tutors"),

    //Case
    CASE_REPORT("Case report"),
    BACKGROUND("Background"),
    CONCLUSION("Conclusion"),

    //Research
    INTRODUCTION("Introduction"),
    AIM_OF_THE_STUDY("Aim of the study"),
    MATERIAL_AND_METHODS("Material and methods"),
    RESULT("Result")
    ;

    private String field;

    Fields(String field) {
        this.field = field;
    }

    public String getField() {
        return field;
    }
}
