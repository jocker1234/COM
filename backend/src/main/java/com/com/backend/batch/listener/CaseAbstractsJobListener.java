package com.com.backend.batch.listener;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CaseAbstractsJobListener implements JobExecutionListener {

    private final Logger log = LoggerFactory.getLogger(CaseAbstractsJobListener.class);

    private DateTime startTime, stopTime;

    @Override
    public void beforeJob(JobExecution jobExecution) {
        startTime = new DateTime();
        log.info("CaseAbstracts Job starts at: " + startTime);
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        stopTime = new DateTime();
        log.info("CaseAbstracts Job stops at: " + stopTime);
        log.info("Totol time take in millis: " + getTimeInMillis(startTime, stopTime));

        if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("CaseAbstracts job completed successfully");
        } else if(jobExecution.getStatus() == BatchStatus.FAILED) {
            log.info("CaseAbstracts job faild with following exceptions ");
            List<Throwable> exceptionList = jobExecution.getAllFailureExceptions();
            for(Throwable th: exceptionList) {
                log.error("exception: " + th.getLocalizedMessage());
            }
        }
    }

    private long getTimeInMillis(DateTime start, DateTime stop){
        return stop.getMillis() - start.getMillis();
    }

}
