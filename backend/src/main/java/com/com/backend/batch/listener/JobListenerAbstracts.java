package com.com.backend.batch.listener;

import com.com.backend.util.Util;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

import javax.batch.runtime.BatchStatus;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import static javax.batch.runtime.BatchStatus.COMPLETED;

@Slf4j
public class JobListenerAbstracts implements JobExecutionListener {
    private final XWPFDocument document;
    private final String path;

    public JobListenerAbstracts(XWPFDocument document) {
        this.document = document;
        path = System.getProperty("user.dir") + "/backend/file/abstracts" + Util.setDate() + ".docx";
    }

    @Override
    public void beforeJob(JobExecution jobExecution) {

    }
    @Override
    public void afterJob(JobExecution jobExecution) {
        BatchStatus batchStatus = jobExecution.getStatus().getBatchStatus();
        if (batchStatus == COMPLETED) {
            try (FileOutputStream fileOutputStream = new FileOutputStream(path)) {
                document.write(fileOutputStream);
                document.close();
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        }
    }
}
