package com.com.backend.dto.response;

import com.com.backend.dto.AbstractDto;
import com.com.backend.dto.CategoryDto;
import lombok.*;
import org.springframework.lang.NonNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AbstractsDtoResponse extends AbstractDto {

    @NonNull
    private String title;
    @NonNull
    private String[] authors;
    @NonNull
    private String tutors;
    @NonNull
    private String affiliation;
    @NonNull
    private String status;
    @NonNull
    private CategoryDto category;
    @NonNull
    private String type;
}
