package com.com.backend.dao;

import com.com.backend.model.Dictionary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DictionaryDao extends JpaRepository<Dictionary, Long> {

    Dictionary findByKey(String key);

}
