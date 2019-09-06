package com.com.backend;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.stream.Collectors;

public class Util {

    public final static String replace(String message, Object[] params) {
        if (params != null && message != null) {
            for (int i = 0; i < params.length; i++) {
                message = message.replaceAll("\\{" + i + "\\}", ((params.length > i && params[i] != null) ? Matcher.quoteReplacement(params[i].toString()) : "null"));
            }
        }
        return message;
    }

    public final static String joinWithComma(List<String> strings) {
        return strings.stream().collect(Collectors.joining(","));
    }

    public final static List<String> splitWithComma(String s){
        return Arrays.stream(s.split(",")).collect(Collectors.toList());

    }
}
