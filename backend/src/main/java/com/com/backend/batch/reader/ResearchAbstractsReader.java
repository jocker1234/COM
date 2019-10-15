package com.com.backend.batch.reader;

import com.com.backend.model.ResearchAbstracts;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

public class ResearchAbstractsReader implements ItemReader<ResearchAbstracts> {

    @Override
    public ResearchAbstracts read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        return null;
    }

}
