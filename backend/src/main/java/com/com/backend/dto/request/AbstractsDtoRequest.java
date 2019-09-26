package com.com.backend.dto.request;

import com.com.backend.dto.AbstractDto;
import lombok.*;
import org.springframework.lang.NonNull;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class AbstractsDtoRequest extends AbstractDto {

    @NonNull
    private String title;
    @NonNull
    private String[] authors;
    @NonNull
    private String tutors;
    @NonNull
    private String status;
    @NonNull
    private Long categoryId;

    private String type;
}
