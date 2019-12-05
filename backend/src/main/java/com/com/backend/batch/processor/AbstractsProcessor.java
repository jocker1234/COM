package com.com.backend.batch.processor;

import com.com.backend.model.Abstracts;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class AbstractsProcessor implements ItemProcessor<Abstracts, Abstracts> {

    @Override
    public Abstracts process(Abstracts item) throws Exception {
        return item;
    }

}

