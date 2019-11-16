package com.com.backend.dto.request;

import com.com.backend.dto.AbstractDto;
import lombok.*;
import org.springframework.lang.NonNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AbstractsDtoRequest extends AbstractDto {

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
    private Long categoryId;
    @NonNull
    private String type;
}
