package com.com.backend.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UsersDto extends AbstractDto {

    private String email;
    private String password;
    private LocalDateTime registrationDate = LocalDateTime.now();
    private Boolean activatedAccount = false;
    private String firstName;
    private String lastName;
    private String gender;
    private LocalDate dateOfBirth;
    private String country;
    private String title;
    private String university;
    private String faculty;
    private Integer yearOfStudy;
    private String phoneNumber;
    private Boolean needVisa;
    private String passportNumber;
    private Set<AuthoritiesDto> authoritiesSet;
    private String[] authorities;

}
