package com.com.backend.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserResponseWithAbstracts extends UserResponse {

    private AbstractsDtoResponse[] abstractDtos;

}
