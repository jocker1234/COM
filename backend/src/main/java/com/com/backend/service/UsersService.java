package com.com.backend.service;

import com.com.backend.domain.Users;
import com.com.backend.dto.UsersDto;
import com.com.backend.exception.AppException;


public interface UsersService extends AbstractService<Users, UsersDto> {

    Users findByEmail(String email);
    UsersDto signUpUser(UsersDto usersDtoRequest) throws AppException;
    boolean existsUserByEmail(String email);
    Long getUserIdByEmail(String email);
    Users getUserByEmail(String email);

    UsersDto updateUser(UsersDto usersDto) throws AppException;
    void deleteUser(Long id);
}
