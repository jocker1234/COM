package com.com.backend.config;

import com.com.backend.batch.listener.CaseAbstractsJobListener;
import com.com.backend.batch.processor.CaseAbstractsItemProcessor;
import com.com.backend.batch.reader.CaseAbstractsReader;
import com.com.backend.batch.writer.FlatFileCaseAbstractsWriter;
import com.com.backend.dto.response.CaseAbstractsDtoResponse;
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
    private FlatFileCaseAbstractsWriter caseAbstractsWriter;
    @Autowired
    private CaseAbstractsJobListener caseAbstractsJobListener;

    @Bean(name = "caseAbstracts")
    public Job job () {
        Step step = stepBuilderFactory.get("export-to-file-step")
                .<CaseAbstracts, CaseAbstractsDtoResponse> chunk(100)
                .reader(caseAbstractsReader)
                .processor(caseAbstractsItemProcessor)
                .writer(caseAbstractsWriter)
                .build();

        return jobBuilderFactory.get("export-to-file")
                                .incrementer(new RunIdIncrementer())
                                .listener(caseAbstractsJobListener)
                                .flow(step)
                                .end()
                                .build();
    }

    /*@Bean
    public Step exportFile() {
        return stepBuilderFactory.get("exportFile")
                .chunk(100)
                .reader(jdbcCursorItemReader())
                .writer(writer())
                .build();
    }

    @Bean
    public Step compressFile() {
        return stepBuilderFactory.get("compressFile")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        try {
                            File file = new File(config.getPath() + "\\" + date + ".zip");
                            ZipOutputStream zop = new ZipOutputStream(new FileOutputStream(file));
                            ZipEntry entry = new ZipEntry(filePath);
                            zop.putNextEntry(entry);
                            zop.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return RepeatStatus.FINISHED;
                    }
                });
    }*/

    }
