package com.com.backend.dao;

import com.com.backend.model.Authorities;
import com.com.backend.model.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthoritiesDao extends JpaRepository<Authorities, Long> {

    Optional<Authorities> findAuthoritiesByRoleName(Role role);

    @Query(value = "select a.role_name " +
            "from users u join users_authorities ua on u.id = ua.user_id join authorities a on ua.authorities_id = a.id " +
            "where u.email like ?1", nativeQuery = true)
    List<String> getUserAuthorities(String email);

}
