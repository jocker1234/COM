package com.com.backend.dao;

import com.com.backend.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface UsersDao extends JpaRepository<Users, Long> {

    Optional<Users> findByEmail(String email);
    Boolean existsByEmail(String email);

    @Query(value = "select u.id from Users u where u.email in (:email)")
    Long getUserIdByEmail(@Param("email") String email);

    Users getUsersByEmail(String email);
    Users getUsersByResetToken(String resetToken);

    @Query(value = "select u.email from Users u where u.id = :id")
    String getEmailById(@Param("id") long id);

    @Query(value = "SELECT u.* FROM users u JOIN users_authorities ua ON u.id = ua.user_id " +
                            "JOIN authorities a ON ua.authorities_id = a.id " +
                            "WHERE a.role_name IN (?1)", nativeQuery = true)
    List<Users> findAllByRole(List<String> list);
}
