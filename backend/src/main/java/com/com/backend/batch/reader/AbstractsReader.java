package com.com.backend.batch.reader;

import com.com.backend.dao.AbstractsDao;
import com.com.backend.dao.ResearchAbstractsDao;
import com.com.backend.model.Abstracts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
@StepScope
public class AbstractsReader extends RepositoryItemReader<Abstracts> {

    private ResearchAbstractsDao abstractsDao;

    public AbstractsReader(ResearchAbstractsDao abstractsDao) {
        this.abstractsDao = abstractsDao;
    }

    @PostConstruct
    public void init() {
        log.info("Start reading");
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
