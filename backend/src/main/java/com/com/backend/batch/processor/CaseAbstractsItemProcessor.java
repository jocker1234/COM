package com.com.backend.batch.processor;

import com.com.backend.dao.CaseAbstractsDao;
import com.com.backend.model.CaseAbstracts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CaseAbstractsItemProcessor implements ItemProcessor<List<CaseAbstracts>, List<CaseAbstracts>> {

    private final Logger log = LoggerFactory.getLogger(CaseAbstractsItemProcessor.class);
    private CaseAbstractsDao caseAbstractsDao;

    public CaseAbstractsItemProcessor(CaseAbstractsDao caseAbstractsDao) {
        this.caseAbstractsDao = caseAbstractsDao;
    }

    @Override
    public List<CaseAbstracts> process(List<CaseAbstracts> caseAbstractsList) throws Exception {
        List<CaseAbstracts> caseAbstractsList1 = caseAbstractsDao.findAll();
        log.info("Processing result: " + caseAbstractsList1);
        return caseAbstractsList1;
    }
}
