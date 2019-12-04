package com.com.backend.config;

import com.com.backend.batch.listener.JobListener;
import com.com.backend.batch.processor.UsersItemProcessor;
import com.com.backend.batch.reader.UsersReader;
import com.com.backend.batch.writer.UsersItemWriter;
import com.com.backend.batch.writer.UsersItemWriter1;
import com.com.backend.dao.UsersDao;
import com.com.backend.model.Users;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    private static final Integer CHUNK = 100;

    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private UsersItemWriter usersWriter;
    @Autowired
    private UsersItemProcessor usersProcessor;
    @Autowired
    private UsersReader usersReader;

    @Bean
    public SXSSFWorkbook workbook() {
        return new SXSSFWorkbook(CHUNK);
    }

    @Bean
    public ItemWriter<Users> usersWriter(SXSSFWorkbook workbook) {
        SXSSFSheet sheet = workbook.createSheet("Users");
        return new UsersItemWriter1(sheet);
    }

    @Bean
    public FileOutputStream fileOutputStream() throws FileNotFoundException {
        return new FileOutputStream("C:/Users/Patryk/Desktop/inzynierka/COM/backend/users.xlsx");
    }

    @Bean
    JobListener jobListener(SXSSFWorkbook workbook, FileOutputStream fileOutputStream, UsersDao usersDao) throws IOException {
        return new JobListener(workbook, fileOutputStream, usersDao);
    }

    @Bean
    Step databaseToCsvFileStep(@Qualifier("usersWriter") ItemWriter<Users> writer) {
        return stepBuilderFactory.get("databaseToCsvFileStep")
                .<Users, Users>chunk(CHUNK)
                .reader(usersReader)
                .processor(usersProcessor)
                .writer(writer)
                .build();
    }

    @Bean(name = "databaseToCsvFileJob")
    Job databaseToCsvFileJob(Step step, @Qualifier("jobListener") JobExecutionListener listener) {
        return jobBuilderFactory.get("databaseToCsvFileJob")
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
