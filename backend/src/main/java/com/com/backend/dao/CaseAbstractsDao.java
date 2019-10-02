package com.com.backend.dao;

import com.com.backend.model.CaseAbstracts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CaseAbstractsDao extends JpaRepository<CaseAbstracts, Long> {

    @Modifying
    @Query("update CaseAbstracts ca set ca.status = :status where ca.id = :caseId")
    int changeStatusCase(@Param("status") String status, @Param("caseId") Long id);

    @Query("select ca.status from CaseAbstracts ca where ca.id = :caseId")
    String getStatus(@Param("caseId") Long id);

    List<CaseAbstracts> getAllByUsersEmail(String email);

    int countCaseAbstractsByUsersEmail(String email);

}
