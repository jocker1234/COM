package com.com.backend.dto;

import lombok.*;
import org.springframework.lang.NonNull;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class CaseAbstractsDto extends AbstractsDto {

    @NonNull
    private String background;
    @NonNull
    private String caseReport;
    @NonNull
    private String conclusions;
}
