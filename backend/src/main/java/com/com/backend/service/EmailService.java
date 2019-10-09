package com.com.backend.service;

import com.com.backend.dto.UsersDto;
import com.com.backend.exception.AppException;
import com.com.backend.model.Abstracts;
import com.com.backend.model.Users;

public interface EmailService {

    void sendCreateEmail(UsersDto user);
    void reamindPassword(Users user);
    void sendDecisionAboutAbstract(Users user, Abstracts abstracts) throws AppException;

}
