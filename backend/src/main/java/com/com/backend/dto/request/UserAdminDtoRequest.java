package com.com.backend.dto.request;

import com.com.backend.dto.AbstractDto;
import com.com.backend.dto.AuthoritiesDto;
import lombok.*;
import org.springframework.lang.NonNull;

import javax.validation.constraints.Email;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserAdminDtoRequest extends AbstractDto {

    @Email
    @NonNull
    private String email;
    private String firstName;
    private String lastName;
    //private String gender;
    private LocalDate dateOfBirth;
    private String country;
    private String title;
    private String university;
    private String faculty;
    private Integer yearOfStudy;
    private String phoneNumber;
    private Boolean needVisa;
    private String passportNumber;

    @NonNull
    private String password;
    private LocalDateTime registrationDate = LocalDateTime.now();
    private Boolean activatedAccount = false;
    private Set<AuthoritiesDto> authoritiesSet;
    private String[] authorities;

}
