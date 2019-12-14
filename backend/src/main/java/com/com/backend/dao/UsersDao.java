package com.com.backend.dao;

import com.com.backend.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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

    @Query(value = "SELECT u.* FROM users u JOIN users_authorities ua ON u.id = ua.user_id " +
            "JOIN authorities a ON ua.authorities_id = a.id " +
            "WHERE a.role_name NOT IN ('ROLE_ADMIN')", nativeQuery = true)
    List<Users> findAllByRoleWithoutAdmin();

    @Query(value = "SELECT DISTINCT(u.*) " +
            "FROM (SELECT u.* " +
                "FROM users u JOIN users_authorities ua on u.id = ua.user_id " +
                             "JOIN authorities a on ua.authorities_id = a.id " +
                "where (:country = '' OR u.country IN (:country)) " +
                        "AND (:university = '' OR u.university IN (:university)) " +
                        "AND (:title = '' OR u.title IN (:title)) " +
                        "AND (:yearOfStudy = 0 OR u.year_of_study IN (:yearOfStudy)) " +
                        "AND a.role_name NOT IN ('ROLE_ADMIN')) u " +
                 "LEFT JOIN case_abstracts ca ON u.id = ca.user_id " +
                 "LEFT JOIN research_abstracts ra ON u.id = ra.user_id " +
                 "LEFT JOIN category c1 ON ca.category_id = c1.id " +
                 "LEFT JOIN category c2 ON ra.category_id = c2.id " +
            "WHERE ((:status = '' OR ca.status IN (:status)) AND (:typeAbstract = '' OR ca.type IN (:typeAbstract)) " +
                        "AND (:nameCategory = '' OR c1.name IN (:nameCategory))) " +
                "OR ((:status = '' OR ra.status IN (:status)) AND (:typeAbstract = '' OR ra.type IN (:typeAbstract)) " +
                        "AND (:nameCategory = '' OR c2.name IN (:nameCategory)))", nativeQuery = true
    )
    List<Users> findAllByParametersWithoutAdmin(
            @Param("country") String country, @Param("university") String university,
            @Param("title") String title, @Param("yearOfStudy") Integer yearOfStudy,
            @Param("status") String status, @Param("typeAbstract") String typeAbstract,
            @Param("nameCategory") String nameCategory
    );
}
