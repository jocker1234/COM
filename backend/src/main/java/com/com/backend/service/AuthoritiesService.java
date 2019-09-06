package com.com.backend.service;

import com.com.backend.domain.Authorities;
import com.com.backend.domain.enums.Role;
import com.com.backend.dto.AuthoritiesDto;

import java.util.Set;

public interface AuthoritiesService {

    Set<AuthoritiesDto> dtosToEntities(Set<Authorities> entities);
    Authorities findByRole(Role roleName);

}
