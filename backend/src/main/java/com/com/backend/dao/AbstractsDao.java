package com.com.backend.dao;

import com.com.backend.model.Abstracts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AbstractsDao extends JpaRepository<Abstracts, Long> {



}
