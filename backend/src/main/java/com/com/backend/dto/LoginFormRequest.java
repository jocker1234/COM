package com.com.backend.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class LoginFormRequest {

    @NotBlank
    @Size(min = 3, max = 64)
    private String email;

    @NotBlank
    @Size(min = 4, max = 128)
    private String password;

}
