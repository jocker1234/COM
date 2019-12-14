package com.com.backend.batch.writer;

import com.com.backend.model.Users;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.batch.item.ItemWriter;

import java.util.Arrays;
import java.util.List;

@Slf4j
public class UsersItemWriterXlsx implements ItemWriter<Users> {

    private final Sheet sheet;

    public UsersItemWriterXlsx(Sheet sheet) {
        this.sheet = sheet;
    }

    @Override
    public void write(List<? extends Users> items) throws Exception {
        log.info("Start writing");
        for(int i = 0; i < items.size(); i++) {
            writeRow(i, items.get(i));
        }
    }

    private void writeRow(int currentRowNumber, Users users) {
        List<String> columns = prepareColumns(users);
        Row row = this.sheet.createRow(currentRowNumber);
        for(int i = 0; i < columns.size(); i++) {
            writeCell(row, i, columns.get(i));
        }
    }

    private List<String> prepareColumns(Users users) {
        return Arrays.asList(
                users.getId().toString(),
                users.getEmail(),
                users.getCountry()
            );
    }

    private void writeCell(Row row, int currentColumnNumber, String value) {
        Cell cell = row.createCell(currentColumnNumber);
        cell.setCellValue(value);
    }

}
