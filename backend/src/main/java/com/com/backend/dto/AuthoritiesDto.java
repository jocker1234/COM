package com.com.backend.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AuthoritiesDto extends AbstractDto {

    @NonNull
    private String roleName;

}
