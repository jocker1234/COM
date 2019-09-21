package com.com.backend.model.enums;

import java.util.HashMap;
import java.util.Map;

public enum YearOfStudy {

    First(1),
    Second(2),
    Third(3),
    Fourth(4),
    Fifth(5),
    Sixth(6);

    private int year;

    YearOfStudy(int year) {
        this.year = year;
    }

    private static Map<Integer, YearOfStudy>map = new HashMap<>();
    static {
        for(YearOfStudy yearOfStudy: YearOfStudy.values()){
            map.put(yearOfStudy.year, yearOfStudy);
        }
    }

    public static YearOfStudy valueOf(int yearOfStudy) {
        return map.get(yearOfStudy);
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return String.valueOf(year);
    }



}
