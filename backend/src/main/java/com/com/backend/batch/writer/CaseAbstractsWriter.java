package com.com.backend.batch.writer;

import com.com.backend.model.CaseAbstracts;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.transform.PassThroughLineAggregator;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CaseAbstractsWriter implements ItemWriter<List<CaseAbstracts>> {

    @Override
    public void write(List<? extends List<CaseAbstracts>> list) throws Exception {
        FlatFileItemWriterBuilder<List<CaseAbstracts>> builder = new FlatFileItemWriterBuilder<>();
        builder.name("caseAbstractWriter").resource(new FileSystemResource("caseAbstracts.txt"))
                .lineAggregator(new PassThroughLineAggregator<>())
                .build();
    }

}
