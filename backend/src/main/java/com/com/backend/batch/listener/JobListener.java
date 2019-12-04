package com.com.backend.batch.listener;

import com.com.backend.dao.UsersDao;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

import javax.batch.runtime.BatchStatus;
import java.io.FileOutputStream;
import java.io.IOException;

import static javax.batch.runtime.BatchStatus.COMPLETED;

@Slf4j
public class JobListener implements JobExecutionListener {
    private final SXSSFWorkbook workbook;
    private final UsersDao usersDao;
    private final FileOutputStream fileOutputStream;

    public JobListener(SXSSFWorkbook workbook, FileOutputStream fileOutputStream, UsersDao usersDao) {
        this.workbook = workbook;
        this.usersDao = usersDao;
        this.fileOutputStream = fileOutputStream;
    }

    @Override
    public void beforeJob(JobExecution jobExecution) {

    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        BatchStatus batchStatus = jobExecution.getStatus().getBatchStatus();
        if (batchStatus == COMPLETED) {
            try {
                workbook.write(fileOutputStream);
                fileOutputStream.close();
                workbook.dispose();
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        }
    }
}
