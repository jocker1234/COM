package com.com.backend.dto.response;

import com.com.backend.dto.AbstractDto;
import com.com.backend.dto.AuthoritiesDto;
import lombok.*;

import javax.validation.constraints.Email;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserAdminDtoResponse extends AbstractDto {

    @Email
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
    private Set<AuthoritiesDto> authoritiesSet;
}
