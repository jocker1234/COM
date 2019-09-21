package com.com.backend.dao;

import com.com.backend.model.Authorities;
import com.com.backend.model.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthoritiesDao extends JpaRepository<Authorities, Long> {

    Optional<Authorities> findAuthoritiesByRoleName(Role role);

}
