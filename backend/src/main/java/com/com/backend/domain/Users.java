package com.com.backend.domain;

import com.com.backend.domain.enums.Gender;
import com.com.backend.domain.enums.Title;
import com.com.backend.domain.enums.YearOfStudy;
import lombok.Getter;
import lombok.Setter;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Users)) return false;
        if (!super.equals(o)) return false;
        Users users = (Users) o;
        return Objects.equals(email, users.email) &&
                Objects.equals(password, users.password) &&
                Objects.equals(registrationDate, users.registrationDate) &&
                Objects.equals(activatedAccount, users.activatedAccount) &&
                Objects.equals(firstName, users.firstName) &&
                Objects.equals(lastName, users.lastName) &&
                gender == users.gender &&
                Objects.equals(dateOfBirth, users.dateOfBirth) &&
                Objects.equals(country, users.country) &&
                title == users.title &&
                Objects.equals(university, users.university) &&
                Objects.equals(faculty, users.faculty) &&
                yearOfStudy == users.yearOfStudy &&
                Objects.equals(phoneNumber, users.phoneNumber) &&
                Objects.equals(needVisa, users.needVisa) &&
                Objects.equals(authoritiesSet, users.authoritiesSet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), email, password, registrationDate, activatedAccount,
                firstName, lastName, gender, dateOfBirth, country, title, university, faculty, yearOfStudy,
                phoneNumber, needVisa, authoritiesSet);
    }
}
