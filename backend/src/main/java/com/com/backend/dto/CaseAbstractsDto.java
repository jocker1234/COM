package com.com.backend.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;

@Getter
@Setter
public class CaseAbstractsDto extends AbstractsDto {

    @NonNull
    private String background;
    @NonNull
    private String caseReport;
    @NonNull
    private String conclusions;
}
