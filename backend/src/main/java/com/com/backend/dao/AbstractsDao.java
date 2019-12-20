package com.com.backend.dao;

import com.com.backend.model.Abstracts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AbstractsDao extends JpaRepository<Abstracts, Long> {

    @Query(value = "SELECT SUM(a.count) " +
            "FROM (SELECT COUNT(*) " +
                    "FROM case_abstracts " +
                    "WHERE user_id = ?1 AND status IN ('A', 'S') " +
            "UNION All " +
                "SELECT COUNT(*) " +
                    "FROM research_abstracts " +
                    "WHERE user_id = ?1 AND status IN ('A', 'S')" +
            ") AS a", nativeQuery = true)
    Long countAllAbstracts(Long userId);

}
