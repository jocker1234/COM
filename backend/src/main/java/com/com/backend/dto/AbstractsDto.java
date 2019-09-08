package com.com.backend.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;

import java.util.List;

@Getter
@Setter
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
