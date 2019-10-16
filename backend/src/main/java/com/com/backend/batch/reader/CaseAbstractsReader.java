package com.com.backend.batch.reader;

import com.com.backend.dao.CaseAbstractsDao;
import com.com.backend.model.CaseAbstracts;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CaseAbstractsReader implements ItemReader<List<CaseAbstracts>> {

    private CaseAbstractsDao caseAbstractsDao;

    public CaseAbstractsReader(CaseAbstractsDao caseAbstractsDao) {
        this.caseAbstractsDao = caseAbstractsDao;
    }

    @Override
    public List<CaseAbstracts> read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        List<CaseAbstracts> caseAbstractsList = caseAbstractsDao.findAll();
        return caseAbstractsList;
    }

}
