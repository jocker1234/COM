package com.com.backend.service;

import com.com.backend.dto.request.UserAdminDtoRequest;
import com.com.backend.dto.request.UserCreateRequest;
import com.com.backend.dto.request.UserUpdateRequest;
import com.com.backend.dto.response.UserAdminDtoResponse;
import com.com.backend.dto.response.UserResponse;
import com.com.backend.dto.response.UserResponseWithAbstracts;
import com.com.backend.exception.AccessException;
import com.com.backend.exception.AppException;
import com.com.backend.exception.NotFoundException;
import com.com.backend.model.Users;

import javax.mail.MessagingException;
import java.util.List;
import java.util.Map;


public interface UsersService extends AbstractService<Users, UserResponse> {

    String getEmailFromToken(String token);
    Long getUserIDFromToken(String token) throws NotFoundException;
    String getEmailFromUserId(long id);
    Users findByEmail(String email);
    UserResponse signUpUser(UserCreateRequest usersDtoRequest) throws AppException;
    boolean existsUserByEmail(String email);
    Long getUserIdByEmail(String email) throws NotFoundException;
    Users getUserByEmail(String email) throws NotFoundException;

    Users setResetToken(Users users);
    void changePassword(Map<String, String> map);

    UserResponse updateUser(UserUpdateRequest usersDto) throws AppException;
    void deleteUser(Long id) throws AppException;

    UserResponseWithAbstracts getOneForAdmin(Long id) throws AppException;

    UserAdminDtoResponse createAdmin(UserAdminDtoRequest usersDtoRequest) throws AppException;

    Boolean checkIfAdmin(String token) throws AccessException;
    List<UserAdminDtoResponse> getAllAdmins(String token);
}
