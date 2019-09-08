package com.com.backend.controller;

import com.com.backend.dto.UsersDto;
import com.com.backend.exception.AppException;
import com.com.backend.service.UsersService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private UsersService usersService;

    public UserController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAll(){
        List<UsersDto> getAll = usersService.getAll();
        return ResponseEntity.ok(getAll);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'ACTIVE_PARTICIPANT', 'PASSIVE_PARTICIPANT')")
    public ResponseEntity<UsersDto> getUser(@Valid @PathVariable Long id) throws AppException {
        UsersDto users = usersService.getOne(id);
        return ResponseEntity.ok(users);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACTIVE_PARTICIPANT', 'ROLE_PASSIVE_PARTICIPANT')")
    public ResponseEntity<?> updateUser(@Valid @PathVariable Long id,@PathParam("isUpdated") String isUpdated,
                                        @Valid @RequestBody UsersDto usersDto) throws AppException {

        if(isUpdated.equals("false"))
            return ResponseEntity.ok(usersDto);

        UsersDto user = usersService.updateUser(usersDto);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACTIVE_PARTICIPANT', 'ROLE_PASSIVE_PARTICIPANT')")
    public ResponseEntity<Void> deleteUser(@Valid @PathVariable Long id) {
        usersService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

}

