package com.com.backend.batch.reader;

import com.com.backend.dao.AbstractsDao;
import com.com.backend.model.Abstracts;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class AbstractsReader extends RepositoryItemReader<Abstracts> {

    private AbstractsDao abstractsDao;

    public AbstractsReader(AbstractsDao abstractsDao) {
        this.abstractsDao = abstractsDao;
    }

    @PostConstruct
    public void init() {
        setRepository(abstractsDao);
        setSort(sortByIdAsc());
        setMethodName("findAll");
    }

    private Map<String, Sort.Direction> sortByIdAsc() {
        Map<String, Sort.Direction> sortConfiguration = new HashMap<>();
        sortConfiguration.put("id", Sort.Direction.ASC);
        return sortConfiguration;
    }

}
