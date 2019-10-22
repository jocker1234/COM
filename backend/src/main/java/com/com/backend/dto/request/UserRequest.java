package com.com.backend.dto.request;

import lombok.*;
import org.springframework.lang.NonNull;

import javax.validation.constraints.Email;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserRequest {

    @Email
    @NonNull
    private String email;
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

}
