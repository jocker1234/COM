package com.com.backend.batch.reader;

import com.com.backend.dao.CaseAbstractsDao;
import com.com.backend.model.CaseAbstracts;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CaseAbstractsReader extends RepositoryItemReader<CaseAbstracts> {

    private CaseAbstractsDao caseAbstractsDao;

    public CaseAbstractsReader(CaseAbstractsDao caseAbstractsDao) {
        super();
        this.caseAbstractsDao = caseAbstractsDao;
    }

    @PostConstruct
    protected void init() {
        final Map<String, Sort.Direction> sorts = new HashMap<>();
        sorts.put("id", Sort.Direction.ASC);
        this.setRepository(caseAbstractsDao);
        this.setSort(sorts);
        this.setMethodName("findAll");
    }

}
