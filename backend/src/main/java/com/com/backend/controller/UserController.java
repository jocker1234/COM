package com.com.backend.controller;

import com.com.backend.dto.Mail;
import com.com.backend.dto.request.UserAdminDtoRequest;
import com.com.backend.dto.request.UserCreateRequest;
import com.com.backend.dto.request.UserUpdateRequest;
import com.com.backend.dto.response.UserAdminDtoResponse;
import com.com.backend.dto.response.UserResponse;
import com.com.backend.dto.response.UserResponseWithAbstracts;
import com.com.backend.exception.AccessException;
import com.com.backend.exception.AppException;
import com.com.backend.model.enums.ExceptionType;
import com.com.backend.service.EmailService;
import com.com.backend.service.UsersService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(value = {"http://localhost:4200", "http://54.37.234.192:4200"})
@RequestMapping("/api/user")
public class UserController {

    private UsersService usersService;
    private EmailService emailService;

    public UserController(UsersService usersService, EmailService emailService) {
        this.usersService = usersService;
        this.emailService = emailService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllWithoutAdmins(@RequestHeader(value = "Authorization")String token,
                                                 @RequestParam Map<String, String> allParams) throws AccessException {
        List<UserResponse> getAll = usersService.getAllUsers(token, allParams);
        return ResponseEntity.ok(getAll);
    }

    @GetMapping("/admins")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllAdmins(@RequestHeader(value = "Authorization")String token) throws AppException {
        if(!usersService.checkIfAdmin(token)) {
            throw new AppException(ExceptionType.NO_ACCESS);
        }
        List<UserAdminDtoResponse> getAll = usersService.getAllAdmins(token);
        return ResponseEntity.ok(getAll);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ACTIVE_PARTICIPANT', 'PASSIVE_PARTICIPANT', 'ADMIN')")
    public ResponseEntity<UserResponse> getUser(@RequestHeader(value = "Authorization")String token,
                                                                    @Valid @PathVariable Long id) throws AppException {
        UserResponse users = usersService.getOne(id, token);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponseWithAbstracts> getUserForAdmin(@Valid @PathVariable Long id) throws AppException {
        UserResponseWithAbstracts users = usersService.getOneForAdmin(id);
        return ResponseEntity.ok(users);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACTIVE_PARTICIPANT', 'ROLE_PASSIVE_PARTICIPANT')")
    public ResponseEntity<?> updateUser(@RequestHeader(value = "Authorization") String token,
                                        @Valid @PathVariable Long id,@PathParam("isUpdated") String isUpdated,
                                        @Valid @RequestBody UserUpdateRequest usersDto) throws AppException {

        String email = usersService.getEmailFromToken(token);

        /*if(!usersService.existsUserByEmail(usersDto.getEmail())) {
            throw new AppException(EntityType.USER, ExceptionType.NOT_FOUND);
        }*/

        if(!usersService.getEmailFromUserId(usersDto.getId()).equals(email)) {
            throw new AppException(ExceptionType.NO_ACCESS);
        }

        if(isUpdated.equals("false"))
            return ResponseEntity.ok(usersDto);

        UserResponse user = usersService.updateUser(usersDto);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACTIVE_PARTICIPANT', 'ROLE_PASSIVE_PARTICIPANT')")
    public ResponseEntity<Void> deleteUser(@RequestHeader(value = "Authorization") String token,
                                           @Valid @PathVariable Long id) throws AppException {
        String email = usersService.getEmailFromToken(token);
        if(!usersService.getUserIdByEmail(email).equals(id)) {
            throw new AppException(ExceptionType.NO_ACCESS);
        }

        usersService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/single-mail")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> sendMail(@Valid @RequestBody Mail mail) {
        emailService.sendSingleMail(mail);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/group-mail")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> sendGroupMail(@Valid @RequestBody Mail mail) {
        emailService.sendGroupMail(mail);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/create-admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> createAdmin(@Valid @RequestBody UserAdminDtoRequest usersDtoRequest) throws AppException {
        if (usersService.existsUserByEmail(usersDtoRequest.getEmail()))
            throw new AppException(ExceptionType.EMAIL_EXIST);
        UserAdminDtoResponse user = usersService.createAdmin(usersDtoRequest);

        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}/admin")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteUserAdmin(@RequestHeader(value = "Authorization") String token,
                                           @Valid @PathVariable Long id) throws AppException {
        String email = usersService.getEmailFromToken(token);
        if(!usersService.getUserIdByEmail(email).equals(id) || !usersService.checkIfAdmin(token)) {
            throw new AppException(ExceptionType.NO_ACCESS);
        }

        usersService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteAllUser(@RequestHeader(value = "Authorization") String token) throws AppException {
        usersService.deleteAllUsers(token);
        return ResponseEntity.ok().build();
    }



}

