package com.com.backend.batch.writer;

import com.com.backend.batch.StringHeaderWriter;
import com.com.backend.model.Users;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.FieldExtractor;
import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

@Component
@StepScope
public class UsersItemWriter extends FlatFileItemWriter<Users> {

    @PostConstruct
    public void init() {
        System.out.println("start writing user ... ");
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT).withZone(ZoneId.systemDefault());
        Instant instant = Instant.now();
        String date = formatter.format(instant);
        date = date.replaceAll("[/,\\s,:]", "-");
        String exportFilePath = "C:/Users/Patryk/Desktop/inzynierka/COM/backend/users"+date+".csv";
        String exportFileHeader = "id;email";

        setHeaderCallback(new StringHeaderWriter(exportFileHeader));
        setResource(new FileSystemResource(exportFilePath));
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
        extractor.setNames(new String[] {"id", "email"});
        return extractor;
    }

}
