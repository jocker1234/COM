package com.com.backend.config;

import com.com.backend.batch.listener.JobListenerAbstracts;
import com.com.backend.batch.listener.JobListenerCsv;
import com.com.backend.batch.processor.AbstractsProcessor;
import com.com.backend.batch.processor.UsersItemProcessor;
import com.com.backend.batch.reader.AbstractsReader;
import com.com.backend.batch.reader.CaseAbstractsReader;
import com.com.backend.batch.reader.UsersReader;
import com.com.backend.batch.writer.AbstractsWriterDocx;
import com.com.backend.batch.writer.UsersItemWriterCsv;
import com.com.backend.batch.writer.UsersItemWriterXlsx;
import com.com.backend.model.Abstracts;
import com.com.backend.model.Users;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;

import java.io.IOException;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    private static final Integer CHUNK = 1000;

    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;


    //USERS
    @Autowired
    private UsersItemProcessor usersProcessor;
    @Autowired
    private UsersReader usersReader;
    @Autowired
    private UsersItemWriterCsv usersItemWriterCsv;
    long randomNumber;

    @Bean
    @RequestScope
    JobListenerCsv jobListenerCsv() throws IOException {
        return new JobListenerCsv();
    }

    @Bean
    @RequestScope
    Step exportToCsvStepAbstracts() {
        return stepBuilderFactory.get("exportToCsvStepAbstracts")
                .<Users, Users>chunk(CHUNK)
                .reader(usersReader)
                .processor(usersProcessor)
                .writer(usersItemWriterCsv)
                .build();
    }

    @Bean(name = "jobExportUsersToCsv")
    Job exportToCsvJob(@Qualifier("exportToCsvStepAbstracts") Step step,
                             @Qualifier("jobListenerCsv") JobExecutionListener listener) {
        return jobBuilderFactory.get("exportToCsvJob")
                .start(step)
                .listener(listener)
                .build();
    }


    //ABSTRACTS
    @Autowired
    private AbstractsProcessor abstractsProcessor;
    @Autowired
    private CaseAbstractsReader caseAbstractsReader;
    @Autowired
    private AbstractsReader abstractsReader;
    XWPFDocument document;
    /*@Bean(name = "document")
    public XWPFDocument document() {
        return new XWPFDocument();
    }*/

    @Bean
    @RequestScope
    public ItemWriter<Abstracts> abstractsWriterDocx() {
        return new AbstractsWriterDocx(document);
    }

    @Bean
    //@StepScope
    @RequestScope
    JobListenerAbstracts jobListenerDocx() throws IOException {
        document = new XWPFDocument();
        return new JobListenerAbstracts(document);
    }

    @Bean
    @RequestScope
    Step exportToDocxStepAbstracts(@Qualifier("abstractsWriterDocx") ItemWriter<Abstracts> writer) {
        return stepBuilderFactory.get("exportToDocxStepAbstracts")
                .<Abstracts, Abstracts>chunk(CHUNK)
                .reader(abstractsReader)
                .processor(abstractsProcessor)
                .writer(writer)
                .build();
    }

    @Bean
    @RequestScope
    Step exportToDocxStepAbstractsC(@Qualifier("abstractsWriterDocx") ItemWriter<Abstracts> writer) {
        return stepBuilderFactory.get("exportToDocxStepAbstracts")
                .<Abstracts, Abstracts>chunk(CHUNK)
                .reader(caseAbstractsReader)
                .processor(abstractsProcessor)
                .writer(writer)
                .build();
    }

    @Bean(name = "jobExportAbstractsToDocx")
    Job jobExportAbstractsToDocx(@Qualifier("exportToDocxStepAbstracts") Step step,
                                 @Qualifier("exportToDocxStepAbstractsC") Step stepC,
                                 @Qualifier("jobListenerDocx") JobExecutionListener listener) {
        return jobBuilderFactory.get("jobExportAbstractsToDocx")
                .start(step)
                .next(stepC)
                .listener(listener)
                .build();
    }







    @Bean
    public SXSSFWorkbook workbook() {
        return new SXSSFWorkbook(CHUNK);
    }

    @Bean
    public ItemWriter<Users> usersWriterXlsx(SXSSFWorkbook workbook) {
        SXSSFSheet sheet = workbook.createSheet("Users");
        return new UsersItemWriterXlsx(sheet);
    }

    @Bean(name = "jobExportUsersToXlsx")
    Job exportToXlsxJob() {
        return null;
    }

}
