package com.com.backend.batch.listener;

import com.com.backend.dao.UsersDao;
import com.com.backend.util.Util;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

import javax.batch.runtime.BatchStatus;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import static javax.batch.runtime.BatchStatus.COMPLETED;

@Slf4j
public class JobListener implements JobExecutionListener {
    private final SXSSFWorkbook workbook;
    private final String path;

    public JobListener(SXSSFWorkbook workbook) {
        this.workbook = workbook;
        path = System.getProperty("user.dir") + "/backend/file/users" + Util.setDate() + ".xlsx";
    }

    @Override
    public void beforeJob(JobExecution jobExecution) {

    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        BatchStatus batchStatus = jobExecution.getStatus().getBatchStatus();
        if (batchStatus == COMPLETED) {
            try (FileOutputStream fileOutputStream = new FileOutputStream(path)) {
                workbook.write(fileOutputStream);
                fileOutputStream.close();
                workbook.dispose();
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        }
    }
}
