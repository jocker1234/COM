package com.com.backend.dto.request;

import com.com.backend.dto.AbstractDto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DirectoryDtoRequest extends AbstractDto {

    @NonNull
    private String key;
    @NonNull
    private String value;

}
