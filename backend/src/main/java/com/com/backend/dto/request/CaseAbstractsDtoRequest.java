package com.com.backend.dto.request;

import lombok.*;
import org.springframework.lang.NonNull;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CaseAbstractsDtoRequest extends AbstractsDtoRequest {

    @NonNull
    private String background;
    @NonNull
    private String caseReport;
    @NonNull
    private String conclusions;
}
