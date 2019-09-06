package com.com.backend.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class AuthoritiesDto extends AbstractDto {

    @NonNull
    private String roleName;

}
