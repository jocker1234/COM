package com.com.backend.validation;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Getter
public class Validation {

    private static final List<String> listCountries = Arrays.asList(Locale.getISOCountries());

    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String PASSPORT_NUMBER_PATTERN = "^[_A-Za-z]{2}[0-9]{7}$";

    private static final Pattern EMAIL = Pattern.compile(EMAIL_PATTERN);
    private static final Pattern PASSPORT_NUMBER = Pattern.compile(PASSPORT_NUMBER_PATTERN);

    public static List<String> getListCountries() {
        return listCountries.stream().map(e -> new Locale("", e).getDisplayCountry(Locale.ENGLISH)).collect(Collectors.toList());
    }

    public static boolean countryValidation(String country){
        return listCountries.stream().anyMatch(e ->
            country.equalsIgnoreCase(new Locale("", e).getDisplayCountry(Locale.ENGLISH)));
    }

    public static boolean emailValidation(String email){
        return EMAIL.matcher(email).matches();
    }

    public static boolean passportNumberValidation(String passportNumber){
        return PASSPORT_NUMBER.matcher(passportNumber).matches();
    }

}
