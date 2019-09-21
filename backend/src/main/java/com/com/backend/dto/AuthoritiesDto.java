package com.com.backend.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class AuthoritiesDto extends AbstractDto {

    @NonNull
    private String roleName;

}
