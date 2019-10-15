package com.com.backend.batch.processor;

import com.com.backend.model.ResearchAbstracts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class ResearchAbstractsItemProcessor implements ItemProcessor<ResearchAbstracts, ResearchAbstracts> {

    private final Logger log = LoggerFactory.getLogger(ResearchAbstractsItemProcessor.class);

    @Override
    public ResearchAbstracts process(ResearchAbstracts caseAbstracts) throws Exception {
        return null;
    }
}
