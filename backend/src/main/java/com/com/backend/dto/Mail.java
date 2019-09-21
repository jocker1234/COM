package com.com.backend.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Mail {

    private String to;
    private String subject;
    private String content;

}
