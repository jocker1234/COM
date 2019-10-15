package com.com.backend.batch.processor;

import com.com.backend.model.CaseAbstracts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class CaseAbstractsItemProcessor implements ItemProcessor<CaseAbstracts, CaseAbstracts> {

    private final Logger log = LoggerFactory.getLogger(CaseAbstractsItemProcessor.class);

    @Override
    public CaseAbstracts process(CaseAbstracts caseAbstracts) throws Exception {
        log.info("Processing result: " + caseAbstracts);
        return caseAbstracts;
    }
}
