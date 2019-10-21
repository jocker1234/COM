package com.com.backend.batch.writer;

import com.com.backend.dto.response.AbstractsDtoResponse;
import com.com.backend.dto.response.CaseAbstractsDtoResponse;
import com.com.backend.model.Category;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.LinkedList;
import java.util.List;

@Component
@StepScope
public class FlatFileCaseAbstractsWriter extends FlatFileItemWriter<CaseAbstractsDtoResponse> {

    private String[] names() {
        List<String> strings = new LinkedList();
        for(Field field: AbstractsDtoResponse.class.getDeclaredFields()) {
            if(field.getType().toString().contains("User")){
                continue;
            } else if(field.getType().toString().contains("CategoryDto")){
                strings.add(field.getName() + "." + Category.class.getDeclaredFields()[0].getName());
            } else {
                strings.add(field.getName());
            }
        }
        for(Field field: CaseAbstractsDtoResponse.class.getDeclaredFields()) {
            strings.add(field.getName());
        }
        String[] names = new String[strings.size()];
        return strings.toArray(names);
    }

    @PostConstruct
    public void init() {
        System.out.println("start writing user ... ");
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT).withZone(ZoneId.systemDefault());
        Instant instant = Instant.now();
        String date = formatter.format(instant);
        date = date.replaceAll("[/,\\s,:]", "-");
        String filePath = "qqq" + date + ".doc";
        this.setResource(new FileSystemResource(filePath));
        this.setAppendAllowed(true);
        this.setLineAggregator(new DelimitedLineAggregator<CaseAbstractsDtoResponse>() {
            {
                setDelimiter("|");
                setFieldExtractor(new BeanWrapperFieldExtractor<CaseAbstractsDtoResponse>() {
                    {

                        setNames(names());
                    }
                });
            }
        });
    }

}
