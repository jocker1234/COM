package com.com.backend.dto;

import lombok.*;
import org.springframework.lang.NonNull;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class AbstractsDto extends AbstractDto {

    @NonNull
    private String title;
    @NonNull
    private List<String> authors;
    @NonNull
    private String tutors;
    @NonNull
    private String status;
    @NonNull
    private Long categoryId;

    private String type;
}
