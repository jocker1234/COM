package com.com.backend.dto.response;

import com.com.backend.dto.AbstractDto;
import lombok.*;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DirectoryDtoResponse extends AbstractDto {

    @NonNull
    private LocalDateTime addDate;
    @NonNull
    private LocalDateTime editDate;
    @NonNull
    private String key;
    private String value;

}
