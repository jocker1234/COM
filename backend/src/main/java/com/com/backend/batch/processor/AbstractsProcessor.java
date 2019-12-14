package com.com.backend.batch.processor;

import com.com.backend.model.Abstracts;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AbstractsProcessor implements ItemProcessor<Abstracts, Abstracts> {

    @Override
    public Abstracts process(Abstracts abstracts) throws Exception {
        log.info("Processing result: " + abstracts);
        return abstracts;
    }

}

