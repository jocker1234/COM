package com.com.backend.config.security;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtResponse {

    private String token;

    private String type = "Bearer";

    private String email;

    private Collection<? extends GrantedAuthority> authorities;

    private Long userId;

    public JwtResponse(String token, Collection<? extends GrantedAuthority> authorities, String email, Long userId) {
        this.token = token;
        this.authorities = authorities;
        this.email = email;
        this.userId = userId;
    }

    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String token) {
        this.token = token;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
