package com.com.backend.dto;

import lombok.*;
import org.springframework.lang.NonNull;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class ResearchAbstractsDto extends AbstractsDto {

    @NonNull
    private String introdution;
    @NonNull
    private String aimOfTheStudy;
    @NonNull
    private String materialAndMethods;
    @NonNull
    private String results;
    @NonNull
    private String conclusions;

}
