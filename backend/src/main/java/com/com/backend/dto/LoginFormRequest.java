package com.com.backend.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class LoginFormRequest {

    @NotBlank
    @Size(min = 3, max = 64)
    private String email;

    @NotBlank
    @Size(min = 4, max = 128)
    private String password;

}
