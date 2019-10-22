package com.com.backend.dto.request;

import lombok.*;
import org.springframework.lang.NonNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ResearchAbstractsDtoRequest extends AbstractsDtoRequest {

    @NonNull
    private String introduction;
    @NonNull
    private String aimOfTheStudy;
    @NonNull
    private String materialAndMethods;
    @NonNull
    private String results;
    @NonNull
    private String conclusions;

}
