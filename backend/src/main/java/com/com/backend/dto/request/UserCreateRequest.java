package com.com.backend.dto.request;

import com.com.backend.dto.AuthoritiesDto;
import com.com.backend.dto.response.AbstractsDtoResponse;
import lombok.*;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserCreateRequest extends UserRequest {

    @NonNull
    private String password;
    private LocalDateTime registrationDate = LocalDateTime.now();
    private Boolean activatedAccount = false;
    private Set<AuthoritiesDto> authoritiesSet;
    private String[] authorities;
    private AbstractsDtoResponse[] abstractDtos;

}
