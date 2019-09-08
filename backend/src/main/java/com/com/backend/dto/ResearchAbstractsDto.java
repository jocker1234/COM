package com.com.backend.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;

@Getter
@Setter
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
