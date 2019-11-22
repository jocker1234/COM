package com.com.backend.service;

import com.com.backend.model.Authorities;
import com.com.backend.model.enums.Role;
import com.com.backend.dto.AuthoritiesDto;

import java.util.List;
import java.util.Set;

public interface AuthoritiesService {

    Set<AuthoritiesDto> dtosToEntities(Set<Authorities> entities);
    Authorities findByRole(Role roleName);
    List<String> getUserAuthorities(String email);

}
