package com.com.backend.dao;

import com.com.backend.model.ResearchAbstracts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResearchAbstractsDao extends JpaRepository<ResearchAbstracts, Long> {

    @Modifying
    @Query("update ResearchAbstracts ra set ra.status = :status where ra.id = :researchId")
    int changeStatusCase(@Param("status") String status, @Param("researchId") Long id);

    @Query("select ra.status from ResearchAbstracts ra where ra.id = :researchId")
    String getStatus(@Param("researchId") Long id);

    List<ResearchAbstracts> getAllByUsersEmail(String email);

    int countCaseAbstractsByUsersEmailAndStatusIn(String email, List<String> statuses);

    @Query(value = "SELECT DISTINCT(ra.*) " +
            "FROM research_abstracts ra JOIN category c ON ra.category_id = c.id " +
            "WHERE (:status = '' OR ra.status IN (:status)) AND (:typeAbstract = '' OR ra.type IN (:typeAbstract)) " +
            "AND (:nameCategory = '' OR c.name IN (:nameCategory))", nativeQuery = true
    )
    List<ResearchAbstracts> findAbstract(
            @Param("status") String status, @Param("typeAbstract") String typeAbstract,
            @Param("nameCategory") String nameCategory
    );

}
