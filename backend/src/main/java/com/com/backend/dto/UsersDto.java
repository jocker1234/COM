package com.com.backend.dto;

import com.com.backend.dto.response.AbstractsDtoResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.lang.NonNull;

import javax.validation.constraints.Email;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UsersDto extends AbstractDto {

    @Email
    @NonNull
    private String email;
    @NonNull
    private String password;
    private LocalDateTime registrationDate = LocalDateTime.now();
    private Boolean activatedAccount = false;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @NonNull
    private String gender;
    @NonNull
    private LocalDate dateOfBirth;
    @NonNull
    private String country;
    @NonNull
    private String title;
    @NonNull
    private String university;
    @NonNull
    private String faculty;
    @NonNull
    private Integer yearOfStudy;
    @NonNull
    private String phoneNumber;
    @NonNull
    private Boolean needVisa;
    private String passportNumber;
    private Set<AuthoritiesDto> authoritiesSet;
    private String[] authorities;
    private AbstractsDtoResponse[] abstractDtos;

}
