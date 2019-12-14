package com.com.backend.batch.listener;

import com.com.backend.util.Util;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.configuration.annotation.StepScope;

import javax.batch.runtime.BatchStatus;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Slf4j
@StepScope
public class JobListenerXlsx implements JobExecutionListener {

    private final SXSSFWorkbook workbook;
    private final String path;
    private DateTime startTime, stopTime;

    public JobListenerXlsx(SXSSFWorkbook workbook) {
        this.workbook = workbook;
        path = System.getProperty("user.dir") + "/backend/file/users-{number}.xlsx";
    }

    @Override
    public void beforeJob(JobExecution jobExecution) {
        startTime = new DateTime();
        log.info("Users Job starts at: " + startTime);
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        stopTime = new DateTime();
        log.info("Users Job stops at: " + stopTime);
        log.info("Tool time take in millis: " + Util.getTimeInMillis(startTime, stopTime));
        BatchStatus batchStatus = jobExecution.getStatus().getBatchStatus();
        if (batchStatus == BatchStatus.COMPLETED) {
            log.info("Users job completed successfully");
            long randomNumber = (long) jobExecution.getJobParameters().getParameters().get("randomNumber").getValue();
            String finalPath = path.replace("{number}", String.valueOf(randomNumber));
            try (FileOutputStream fileOutputStream = new FileOutputStream(finalPath)) {
                workbook.write(fileOutputStream);
                fileOutputStream.close();
                workbook.dispose();
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        } else if(batchStatus == BatchStatus.FAILED) {
            log.info("Users job fail with following exceptions ");
            List<Throwable> exceptionList = jobExecution.getAllFailureExceptions();
            for(Throwable th: exceptionList) {
                log.error("exception: " + th.getLocalizedMessage());
            }
        }
    }
}
