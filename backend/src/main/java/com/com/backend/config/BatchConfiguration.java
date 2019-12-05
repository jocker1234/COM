package com.com.backend.config;

import com.com.backend.batch.listener.JobListener;
import com.com.backend.batch.listener.JobListenerAbstracts;
import com.com.backend.batch.processor.AbstractsProcessor;
import com.com.backend.batch.processor.UsersItemProcessor;
import com.com.backend.batch.reader.AbstractsReader;
import com.com.backend.batch.reader.UsersReader;
import com.com.backend.batch.writer.AbstractsWriter;
import com.com.backend.batch.writer.UsersItemWriter;
import com.com.backend.batch.writer.UsersItemWriter1;
import com.com.backend.dao.UsersDao;
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

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    private static final Integer CHUNK = 1000;

    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private UsersItemProcessor usersProcessor;
    @Autowired
    private UsersReader usersReader;
    @Autowired
    private AbstractsProcessor abstractsProcessor;
    @Autowired
    private AbstractsReader abstractsReader;

    @Bean
    public SXSSFWorkbook workbook() {
        return new SXSSFWorkbook(CHUNK);
    }

    @Bean
    public ItemWriter<Users> usersWriterXlsx(SXSSFWorkbook workbook) {
        SXSSFSheet sheet = workbook.createSheet("Users");
        return new UsersItemWriter1(sheet);
    }

    @Bean
    JobListener jobListenerXlsx(SXSSFWorkbook workbook) throws IOException {
        return new JobListener(workbook);
    }

    @Bean
    Step exportToXlsxStepAbstracts(@Qualifier("usersWriterXlsx") ItemWriter<Users> writer) {
        return stepBuilderFactory.get("exportToXlsxStepAbstracts")
                .<Users, Users>chunk(CHUNK)
                .reader(usersReader)
                .processor(usersProcessor)
                .writer(writer)
                .build();
    }

    @Bean(name = "jobExportUsersToXlsx")
    Job exportToXlsxJob(@Qualifier("exportToXlsxStepAbstracts") Step step,
                             @Qualifier("jobListenerXlsx") JobExecutionListener listener) {
        return jobBuilderFactory.get("exportToXlsxJob")
                .start(step)
                .listener(listener)
                .build();
    }

    @Bean
    public XWPFDocument document() {
        return new XWPFDocument();
    }

    @Bean
    public ItemWriter<Abstracts> abstractsWriterDocx(XWPFDocument document) {
        return new AbstractsWriter(document);
    }

    @Bean
    JobListenerAbstracts jobListenerDocx(XWPFDocument document) throws IOException {
        return new JobListenerAbstracts(document);
    }

    @Bean
    Step exportToDocxStepAbstracts(@Qualifier("abstractsWriterDocx") ItemWriter<Abstracts> writer) {
        return stepBuilderFactory.get("exportToDocxStepAbstracts")
                .<Abstracts, Abstracts>chunk(CHUNK)
                .reader(abstractsReader)
                .processor(abstractsProcessor)
                .writer(writer)
                .build();
    }

    @Bean(name = "jobExportAbstractsToDocx")
    Job jobExportAbstractsToDocx(@Qualifier("exportToDocxStepAbstracts") Step step,
                                 @Qualifier("jobListenerDocx") JobExecutionListener listener) {
        return jobBuilderFactory.get("jobExportAbstractsToDocx")
                .start(step)
                .listener(listener)
                .build();
    }

    /*@Autowired
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
    }*/

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
