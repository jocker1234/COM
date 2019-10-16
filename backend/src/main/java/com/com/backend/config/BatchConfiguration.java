package com.com.backend.config;

import com.com.backend.batch.CaseAbstractsJobListener;
import com.com.backend.batch.processor.CaseAbstractsItemProcessor;
import com.com.backend.batch.reader.CaseAbstractsReader;
import com.com.backend.batch.writer.CaseAbstractsWriter;
import com.com.backend.model.CaseAbstracts;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.function.Function;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private CaseAbstractsReader caseAbstractsReader;
    @Autowired
    private CaseAbstractsItemProcessor caseAbstractsItemProcessor;
    @Autowired
    private CaseAbstractsWriter caseAbstractsWriter;
    @Autowired
    private CaseAbstractsJobListener caseAbstractsJobListener;

    @Bean(name = "caseAbstracts")
    public Job job () {
        Step step = stepBuilderFactory.get("export-to-file-step")
                .<List<CaseAbstracts>, List<CaseAbstracts>> chunk(100)
                .reader(caseAbstractsReader)
                .processor(caseAbstractsItemProcessor)
                .writer(caseAbstractsWriter)
                .build();

        return jobBuilderFactory.get("export-to-file")
                                .incrementer(new RunIdIncrementer())
                                .listener(caseAbstractsJobListener)
                                .start(step)
                                .build();
    }

}
