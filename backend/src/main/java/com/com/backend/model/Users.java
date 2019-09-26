package com.com.backend.model;

import com.com.backend.model.enums.Gender;
import com.com.backend.model.enums.Title;
import com.com.backend.model.enums.YearOfStudy;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class Users extends AbstractEntity {

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, columnDefinition = "timestamp default CURRENT_TIMESTAMP")
    private LocalDateTime registrationDate;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private Boolean activatedAccount;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @Column(nullable = false)
    private String country;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Title title;

    @Column(nullable = false)
    private String university;

    @Column(nullable = false)
    private String faculty;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private YearOfStudy yearOfStudy;

    @Column(length = 9)
    private String phoneNumber;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private Boolean needVisa;

    private String passportNumber;

    @ManyToMany
    @JoinTable(name = "users_authorities",
            joinColumns = @JoinColumn(
                    name = "user_id"),
            inverseJoinColumns = @JoinColumn(
                    name = "authorities_id"))
    @Column(nullable = false)
    private Set<Authorities> authoritiesSet;



}
