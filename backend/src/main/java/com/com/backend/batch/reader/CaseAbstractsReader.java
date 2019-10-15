package com.com.backend.batch.reader;

import com.com.backend.model.CaseAbstracts;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

public class CaseAbstractsReader implements ItemReader<CaseAbstracts> {

    @Override
    public CaseAbstracts read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        return null;
    }

}
