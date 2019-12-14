package com.com.backend.batch.listener;

import com.com.backend.util.Util;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.joda.time.DateTime;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.configuration.annotation.StepScope;

import javax.batch.runtime.BatchStatus;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Slf4j
//@StepScope
public class JobListenerAbstracts implements JobExecutionListener {

    private final XWPFDocument document;
    private final String path;
    private DateTime startTime, stopTime;

    public JobListenerAbstracts(XWPFDocument document) {
        this.document = document;
        path = System.getProperty("user.dir") + "/backend/file/abstracts-{number}.docx";
    }

    @Override
    public void beforeJob(JobExecution jobExecution) {
        startTime = new DateTime();
        log.info("Abstracts Job starts at: " + startTime);
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        stopTime = new DateTime();
        log.info("Abstracts Job stops at: " + stopTime);
        log.info("Tool time take in millis: " + Util.getTimeInMillis(startTime, stopTime));
        BatchStatus batchStatus = jobExecution.getStatus().getBatchStatus();
        if (batchStatus == BatchStatus.COMPLETED) {
            log.info("Abstracts job completed successfully");
            long randomNumber = (long) jobExecution.getJobParameters().getParameters().get("randomNumber").getValue();
            String finalPath = path.replace("{number}", String.valueOf(randomNumber));
            try (FileOutputStream fileOutputStream = new FileOutputStream(finalPath)) {
                document.write(fileOutputStream);
                document.close();
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        } else if(batchStatus == BatchStatus.FAILED) {
            log.info("Abstracts job fail with following exceptions ");
            List<Throwable> exceptionList = jobExecution.getAllFailureExceptions();
            for(Throwable th: exceptionList) {
                log.error("exception: " + th.getLocalizedMessage());
            }
        }
    }
}
