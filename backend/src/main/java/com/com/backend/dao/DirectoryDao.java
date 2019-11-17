package com.com.backend.dao;

import com.com.backend.model.Directory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectoryDao extends JpaRepository<Directory, Long> {
}
