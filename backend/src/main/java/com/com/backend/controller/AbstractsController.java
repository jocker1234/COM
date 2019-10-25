package com.com.backend.controller;

import com.com.backend.model.Abstracts;
import com.com.backend.service.AbstractsService;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(value = {"http://localhost:4200", "http://54.37.234.192:4200"})
@RequestMapping("/abstracts")
public class AbstractsController {

    @Autowired
    private AbstractsService abstractsService;

    @Autowired
    @Qualifier("caseAbstracts")
    private Job job;
    @Autowired
    private JobLauncher jobLauncher;

    /*public BatchStatus exportAbstracts() throws JobExecutionAlreadyRunningException, JobRestartException,
            JobInstanceAlreadyCompleteException, JobParametersInvalidException {
        Map<String, JobParameter> maps = new HashMap<>();
        maps.put("time", new JobParameter(System.currentTimeMillis()));
        JobParameters parameters = new JobParameters(maps);
        JobExecution jobExecution = jobLauncher.run(job, parameters);
        System.out.println("Job Execution " + jobExecution.getStatus());
        System.out.println("Batch is running..");
        while(jobExecution.isRunning()) {
            System.out.println("...");
        }
        return jobExecution.getStatus();
    }*/

    @RequestMapping("/export")
    public String handle() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("time",System.currentTimeMillis()).toJobParameters();
        jobLauncher.run(job, jobParameters);
        return "Batch job has been invoked";
    }

    @GetMapping("/allUserAbstracts")
    public ResponseEntity AbstractsForUser(@RequestHeader(value = "Authorization")String token) {
        List<Abstracts> abstractsList = abstractsService.getAllAbstractUser(token);
        return ResponseEntity.ok(abstractsList);
    }
}
