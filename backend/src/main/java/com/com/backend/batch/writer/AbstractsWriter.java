package com.com.backend.batch.writer;

import com.com.backend.model.Abstracts;
import com.com.backend.model.CaseAbstracts;
import com.com.backend.model.ResearchAbstracts;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.batch.item.ItemWriter;

import java.util.Arrays;
import java.util.List;

public class AbstractsWriter implements ItemWriter<Abstracts> {

    private final XWPFDocument document;

    public AbstractsWriter(XWPFDocument document) {
        this.document = document;
    }

    @Override
    public void write(List<? extends Abstracts> items) throws Exception {
        items.stream().forEach(item -> {
            writeSiteAbstract(item);
        });
    }

    private void writeSiteAbstract(Abstracts abstracts) {
        List<String> columns = prepareParagraphData(abstracts);
        XWPFParagraph paragraph = null;
        for (int i = 0; i < columns.size(); i++){
            paragraph = this.document.createParagraph();
            writeParagraph(paragraph, columns.get(i), "Left");
            writeParagraph(paragraph, columns.get(i), "Right");
        }
        paragraph.setPageBreak(true);
    }

    private List<String> prepareParagraphData(Abstracts abstracts) {
        return Arrays.asList(
                abstracts.getId().toString(),
                abstracts.getTitle(),
                abstracts.getAuthors().toString()
        );
    }

    private List<String> prepareParagraphCase(CaseAbstracts abstracts) {
        return Arrays.asList(
                abstracts.getCaseReport(),
                abstracts.getBackground(),
                abstracts.getConclusions()
        );
    }

    private List<String> prepareParagraphResearch(ResearchAbstracts abstracts) {
        return Arrays.asList(
                abstracts.getIntroduction(),
                abstracts.getAimOfTheStudy(),
                abstracts.getMaterialAndMethods(),
                abstracts.getResults(),
                abstracts.getConclusions()
        );
    }

    private void writeParagraph(XWPFParagraph paragraph, String value, String position) {
        XWPFRun paragraphRun = paragraph.createRun();
        paragraphRun.setText(value);
    }

}
