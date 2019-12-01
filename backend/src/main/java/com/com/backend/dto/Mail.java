package com.com.backend.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Mail {

    private String to;
    private String[] multiTo;
    private String subject;
    private String content;

}
