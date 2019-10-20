package com.com.backend.service;

import com.com.backend.exception.NotFoundException;
import com.com.backend.model.Abstracts;
import com.com.backend.model.Users;
import com.com.backend.dto.UsersDto;
import com.com.backend.exception.AppException;

import java.util.List;


public interface UsersService extends AbstractService<Users, UsersDto> {

    String getEmailFromToken(String token);
    Users findByEmail(String email);
    UsersDto signUpUser(UsersDto usersDtoRequest) throws AppException;
    boolean existsUserByEmail(String email);
    Long getUserIdByEmail(String email) throws NotFoundException;
    Users getUserByEmail(String email) throws NotFoundException;

    UsersDto updateUser(UsersDto usersDto) throws AppException;
    void deleteUser(Long id) throws AppException;

    UsersDto getOneForAdmin(Long id) throws AppException;
}
