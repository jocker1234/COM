package com.com.backend.dao;

import com.com.backend.model.CaseAbstracts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CaseAbstractsDao extends PagingAndSortingRepository<CaseAbstracts, Long>, JpaRepository<CaseAbstracts, Long> {

    @Modifying
    @Query("update CaseAbstracts ca set ca.status = :status where ca.id = :caseId")
    int changeStatusCase(@Param("status") String status, @Param("caseId") Long id);

    @Query("select ca.status from CaseAbstracts ca where ca.id = :caseId")
    String getStatus(@Param("caseId") Long id);

    List<CaseAbstracts> getAllByUsersEmail(String email);

    int countCaseAbstractsByUsersEmailAndStatusIn(String email, List<String> statuses);

    List<CaseAbstracts> getAllByIdIsNotNull();

    @Query(value = "SELECT DISTINCT(ca.*) " +
            "FROM case_abstracts ca JOIN category c ON ca.category_id = c.id " +
            "WHERE (:status = '' OR ca.status IN (:status)) AND (:typeAbstract = '' OR ca.type IN (:typeAbstract)) " +
            "AND (:nameCategory = '' OR c.name IN (:nameCategory))", nativeQuery = true
    )
    List<CaseAbstracts> findAbstract(
            @Param("status") String status, @Param("typeAbstract") String typeAbstract,
            @Param("nameCategory") String nameCategory
    );

}
