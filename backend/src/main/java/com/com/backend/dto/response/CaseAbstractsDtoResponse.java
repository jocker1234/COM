package com.com.backend.dto.response;

import lombok.*;
import org.springframework.lang.NonNull;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CaseAbstractsDtoResponse extends AbstractsDtoResponse {

    @NonNull
    private String background;
    @NonNull
    private String caseReport;
    @NonNull
    private String conclusions;
}
