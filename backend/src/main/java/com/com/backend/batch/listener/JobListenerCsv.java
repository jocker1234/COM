package com.com.backend.batch.listener;

import com.com.backend.util.Util;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.joda.time.DateTime;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.configuration.annotation.StepScope;

import javax.batch.api.listener.JobListener;
import javax.batch.runtime.BatchStatus;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Slf4j
public class JobListenerCsv implements JobExecutionListener {

    private DateTime startTime, stopTime;

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
        } else if(batchStatus == BatchStatus.FAILED) {
            log.info("Users job fail with following exceptions ");
            List<Throwable> exceptionList = jobExecution.getAllFailureExceptions();
            for(Throwable th: exceptionList) {
                log.error("exception: " + th.getLocalizedMessage());
            }
        }
    }
}
