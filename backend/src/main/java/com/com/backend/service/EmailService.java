package com.com.backend.service;

import com.com.backend.domain.Users;
import com.com.backend.dto.UsersDto;

public interface EmailService {

    void sendCreateEmail(UsersDto user);
    void reamindPassword(Users user);

}
