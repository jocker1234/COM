package com.com.backend.service.serviceImpl;

import com.com.backend.dao.AuthoritiesDao;
import com.com.backend.model.Authorities;
import com.com.backend.model.enums.Role;
import com.com.backend.dto.AuthoritiesDto;
import com.com.backend.mapper.AuthoritiesMapper;
import com.com.backend.service.AuthoritiesService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Transactional
public class AuthoritiesServiceImpl implements AuthoritiesService {

    private AuthoritiesMapper authoritiesMapper;
    private AuthoritiesDao authoritiesDao;

    public AuthoritiesServiceImpl(AuthoritiesMapper authoritiesMapper, AuthoritiesDao authoritiesDao) {
        this.authoritiesMapper = authoritiesMapper;
        this.authoritiesDao = authoritiesDao;
    }

    @Override
    public Set<AuthoritiesDto> dtosToEntities(Set<Authorities> entities) {
        return authoritiesMapper.authoritiessToAuthoritiesDtos(entities);
    }

    @Override
    public Authorities findByRole(Role roleName) {
        return authoritiesDao.findAuthoritiesByRoleName(roleName)
                .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
    }

}
