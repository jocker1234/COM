package com.com.backend.util;

import org.joda.time.DateTime;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.stream.Collectors;

public class Util {

    public static String replace(String message, Object[] params) {
        if (params != null && message != null) {
            for (int i = 0; i < params.length; i++) {
                message = message.replaceAll("\\{" + i + "\\}", params[i] != null ? Matcher.quoteReplacement(params[i].toString()) : "null");
            }
        }
        return message;
    }

    public static String joinWithComma(List<String> strings) {
        return String.join(",", strings);
    }

    public static List<String> splitWithComma(String s) {
        return Arrays.stream(s.split(",")).collect(Collectors.toList());
    }

    public static boolean isNull(String value) {
        return !(value != null && !value.isEmpty());
    }

    public static boolean isNull(Object value) {
        return value == null;
    }

    public static String setDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT).withZone(ZoneId.systemDefault());
        Instant instant = Instant.now();
        String date = formatter.format(instant);
        return date.replaceAll("[/,\\s,:]", "-");
    }

    public static long getTimeInMillis(DateTime start, DateTime stop){
        return stop.getMillis() - start.getMillis();
    }

}
