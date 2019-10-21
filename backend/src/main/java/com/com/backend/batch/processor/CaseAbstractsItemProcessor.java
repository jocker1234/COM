package com.com.backend.batch.processor;

import com.com.backend.dto.response.CaseAbstractsDtoResponse;
import com.com.backend.mapper.CaseAbstractsMapper;
import com.com.backend.model.CaseAbstracts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CaseAbstractsItemProcessor implements ItemProcessor<CaseAbstracts, CaseAbstractsDtoResponse> {

    private final Logger log = LoggerFactory.getLogger(CaseAbstractsItemProcessor.class);
    @Autowired
    private CaseAbstractsMapper caseAbstractsMapper;

    @Override
    public CaseAbstractsDtoResponse process(CaseAbstracts caseAbstractsList) throws Exception {
        log.info("Processing result: " + caseAbstractsList);
        return caseAbstractsMapper.modelToDtoRes(caseAbstractsList);
    }
}
