package com.com.backend.batch.writer;

import com.com.backend.batch.callback.StringHeaderCallbackWriter;
import com.com.backend.model.Users;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.FieldExtractor;
import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Component
@StepScope
public class UsersItemWriterCsv extends FlatFileItemWriter<Users> {

    @Value("#{jobParameters['randomNumber']}")
    long randomNumber;

    @PostConstruct
    public void init() {
        log.info("Start writing");
        String exportFilePath = System.getProperty("user.dir") + "/backend/file/users-" + randomNumber + ".csv";
        String exportFileHeader = "id;email;firstName;lastName;title;country;dateOfBirth;university;faculty;yearOfStudy;phoneNumber;needVisa;passportNumber";

        setResource(new FileSystemResource(exportFilePath));
        setHeaderCallback(new StringHeaderCallbackWriter(exportFileHeader));
        setAppendAllowed(true);
        setLineAggregator(createUsersLineAggregator());
    }

    private LineAggregator<Users> createUsersLineAggregator() {
        DelimitedLineAggregator<Users> lineAggregator = new DelimitedLineAggregator<>();
        lineAggregator.setDelimiter(";");
        FieldExtractor<Users> fieldExtractor = createUsersFieldExtractor();
        lineAggregator.setFieldExtractor(fieldExtractor);
        return lineAggregator;
    }

    private FieldExtractor<Users> createUsersFieldExtractor() {
        BeanWrapperFieldExtractor<Users> extractor = new BeanWrapperFieldExtractor<>();
        extractor.setNames(new String[]{"id","email","firstName","lastName","title","country","dateOfBirth",
                "university","faculty","yearOfStudy","phoneNumber","needVisa","passportNumber"});
        return extractor;
    }
}
