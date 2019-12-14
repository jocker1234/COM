package com.com.backend.controller;

import com.com.backend.exception.AppException;
import com.com.backend.model.Abstracts;
import com.com.backend.service.AbstractsService;
import com.com.backend.service.CaseAbstractsService;
import com.com.backend.service.ResearchAbstractsService;
import com.com.backend.service.UsersService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Random;

@RestController
@CrossOrigin(value = {"http://localhost:4200", "http://54.37.234.192:4200"})
@RequestMapping("/abstracts")
public class AbstractsController {

    @Autowired
    private AbstractsService abstractsService;
    @Autowired
    private CaseAbstractsService caseAbstractsService;
    @Autowired
    private ResearchAbstractsService researchAbstractsService;
    @Autowired
    private UsersService usersService;

    @Autowired
    @Qualifier("jobExportAbstractsToDocx")
    private Job jobA;
    @Autowired
    private JobLauncher jobLauncherA;

    @RequestMapping(value = "/exportAbstract", produces = "application/octet-stream")
    public ResponseEntity handleA(HttpServletResponse response) throws Exception {
        Random random = new Random();
        int randomNumber = random.nextInt(9999) + 1;
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .addLong("randomNumber", (long) randomNumber)
                .toJobParameters();
        jobLauncherA.run(jobA, jobParameters);
        String path = System.getProperty("user.dir") + "/backend/file/abstracts-" + randomNumber + ".docx";
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.parseMediaType("application/octet-stream"));
        header.add("filename", "users.xlsx");
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=abstracts-" + randomNumber + ".docx");
        ResponseEntity responseEntity = new ResponseEntity<>(
                new FileSystemResource("C:/Users/Patryk/Desktop/inzynierka/COM/backend/file/abstracts-" + randomNumber + ".docx"),
                header, HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping("/allUserAbstracts")
    public ResponseEntity AbstractsForUser(@RequestHeader(value = "Authorization") String token) {
        List<Abstracts> abstractsList = abstractsService.getAllAbstractUser(token);
        return ResponseEntity.ok(abstractsList);
    }

    @GetMapping("/countUserAbstracts")
    public ResponseEntity countAbstractsForUser(@RequestHeader(value = "Authorization") String token) throws AppException {
        String email = usersService.getEmailFromToken(token);
        long count = abstractsService.countAllAbstractUser(email);
        return ResponseEntity.ok(count);
    }

    @DeleteMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> deleteAllAbstracts(@RequestHeader(value = "Authorization") String token)
            throws AppException {
        caseAbstractsService.deleteAllAbstracts(token);
        researchAbstractsService.deleteAllAbstracts(token);
        return ResponseEntity.noContent().build();
    }
}
