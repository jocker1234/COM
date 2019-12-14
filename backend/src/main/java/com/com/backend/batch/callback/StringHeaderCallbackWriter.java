package com.com.backend.batch.callback;

import org.springframework.batch.item.file.FlatFileHeaderCallback;

import java.io.IOException;
import java.io.Writer;

public class StringHeaderCallbackWriter implements FlatFileHeaderCallback {

    private final String header;

    public StringHeaderCallbackWriter(String header) {
        this.header = header;
    }

    @Override
    public void writeHeader(Writer writer) throws IOException {
        writer.write(header);
    }

}
