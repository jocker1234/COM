package com.com.backend.dao;

import com.com.backend.model.CaseAbstracts;
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

    int countResearchAbstractsByUsersEmail(String email);

}
