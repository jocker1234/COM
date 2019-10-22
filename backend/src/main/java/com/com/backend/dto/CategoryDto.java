package com.com.backend.dto;

import lombok.*;
import org.springframework.lang.NonNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CategoryDto extends AbstractDto {

    @NonNull
    private String name;

}
