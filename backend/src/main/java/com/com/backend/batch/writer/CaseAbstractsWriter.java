package com.com.backend.batch.writer;

import com.com.backend.model.CaseAbstracts;
import com.lowagie.text.Table;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Component
public class CaseAbstractsWriter implements ItemWriter<CaseAbstracts> {

    @Override
    public void write(List<? extends CaseAbstracts> list) throws Exception {

    }
}
