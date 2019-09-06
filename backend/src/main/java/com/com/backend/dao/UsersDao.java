package com.com.backend.dao;

import com.com.backend.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersDao extends JpaRepository<Users, Long> {

    Optional<Users> findByEmail(String email);
    Boolean existsByEmail(String email);

    @Query(value = "select u.id from Users u where u.email in (:email)")
    Long getUserIdByEmail(@Param("email") String email);

    Users getUsersByEmail(String email);

}
