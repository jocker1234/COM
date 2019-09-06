package com.com.backend.config.security;

import com.com.backend.domain.Users;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@Setter
public class UserPrinciple implements UserDetails {

    private static final long serialVersionUID = 1L;

    private Long id;
    @JsonIgnore
    private String password;
    private String email;
    private Collection<? extends GrantedAuthority> authorities;

    private UserPrinciple(Long id, String userName, String password
            , String email, Collection<? extends GrantedAuthority> authorities) {

        this.id = id;
        this.password = password;
        this.email = email;
        this.authorities = authorities;
    }

    public static UserPrinciple build(Users user) {
        List<GrantedAuthority> authorities = user.getAuthoritiesSet().stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName().name())).collect(Collectors.toList());

        return new UserPrinciple(user.getId(), user.getEmail(), user.getPassword(), user.getEmail(), authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserPrinciple)) return false;
        UserPrinciple that = (UserPrinciple) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(password, that.password) &&
                Objects.equals(email, that.email) &&
                Objects.equals(authorities, that.authorities);
    }

}
