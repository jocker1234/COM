package com.com.backend.controller;

import com.com.backend.config.security.JwtProvider;
import com.com.backend.config.security.JwtResponse;
import com.com.backend.dto.request.UserCreateRequest;
import com.com.backend.dto.response.UserResponse;
import com.com.backend.exception.NotFoundException;
import com.com.backend.model.Users;
import com.com.backend.model.enums.ExceptionType;
import com.com.backend.dto.LoginFormRequest;
import com.com.backend.exception.AppException;
import com.com.backend.service.EmailService;
import com.com.backend.service.UsersService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

@RestController
@CrossOrigin(value = {"http://localhost:4200", "http://54.37.234.192:4200"})
@RequestMapping("/api/auth")
public class SignUpInController {

    private AuthenticationManager authenticationManager;
    private JwtProvider jwtProvider;
    private UsersService userService;
    private EmailService emailService;

    public SignUpInController(AuthenticationManager authenticationManager,
                              JwtProvider jwtProvider,
                              UsersService userService,
                              EmailService emailService) {

        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
        this.userService = userService;
        this.emailService = emailService;
    }

    @PostMapping("/signin")
    public JwtResponse authenticationUser(@Valid @RequestBody LoginFormRequest loginRequest) throws NotFoundException {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        Long userId = userService.getUserIdByEmail(userDetails.getUsername());

        return new JwtResponse(jwt, userDetails.getAuthorities(), userDetails.getUsername(), userId);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registrationUser(@Valid @RequestBody UserCreateRequest usersDtoRequest) throws AppException {
        if (userService.existsUserByEmail(usersDtoRequest.getEmail()))
            throw new AppException(ExceptionType.EMAIL_EXIST);
        UserResponse user = userService.signUpUser(usersDtoRequest);

        return ResponseEntity.ok(user);
    }

    @PostMapping("/reset")
    public ResponseEntity<?> resetPassword(@Valid @RequestBody String email) throws AppException {
        if (email == null)
            throw new AppException(ExceptionType.EMAIL_EXIST);

        Users user = userService.getUserByEmail(email);
        emailService.reamindPassword(user);

        return ResponseEntity.ok().build();
    }

    public void exit(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, null, null);
        try {
            response.sendRedirect(request.getHeader("http://localhost:4200"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
