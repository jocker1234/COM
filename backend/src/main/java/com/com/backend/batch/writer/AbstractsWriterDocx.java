package com.com.backend.batch.writer;

import com.com.backend.model.Abstracts;
import com.com.backend.model.CaseAbstracts;
import com.com.backend.model.ResearchAbstracts;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Footer;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.batch.item.ItemWriter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class AbstractsWriterDocx implements ItemWriter<Abstracts> {

    private final XWPFDocument document;

    public AbstractsWriterDocx(XWPFDocument document) {
        this.document = document;
    }

    @Override
    public void write(List<? extends Abstracts> items) throws Exception {
        log.info("Start writing");
        items.stream().forEach(item -> {
            writeSiteAbstract(item);
        });
        log.info("End writing");
    }

    private void writeSiteAbstract(Abstracts abstracts) {
        List<String[]> basicColumns = prepareParagraphData(abstracts);
        List<String[]> advancedClumns = null;
        if(abstracts instanceof CaseAbstracts){
            advancedClumns = prepareParagraphCase((CaseAbstracts) abstracts);
        } else if(abstracts instanceof ResearchAbstracts) {
            advancedClumns = prepareParagraphResearch((ResearchAbstracts) abstracts);
        }
        XWPFParagraph paragraph = null;
        for (String[] value: basicColumns){
            paragraph = this.document.createParagraph();
            writeParagraph(paragraph, value);
        }
        paragraph = this.document.createParagraph();
        for (String[] value: advancedClumns){
            paragraph = this.document.createParagraph();
            writeParagraph(paragraph, value);
        }
        paragraph = this.document.createParagraph();
        paragraph.setPageBreak(true);
    }

    private List<String[]> prepareParagraphData(Abstracts abstracts) {
        StringBuilder authors = new StringBuilder(abstracts.getAuthors()[0]);
        for (int i = 1; i < abstracts.getAuthors().length; i++) {
            authors.append(", " + abstracts.getAuthors()[i]);
        }
        return Arrays.asList(
                new String[]{"Title: ", abstracts.getTitle()},
                new String[]{"Authors: ", authors.toString()},
                new String[]{"Tutor/Tutors: ", abstracts.getTutors()},
                new String[]{"University: ", abstracts.getAffiliation()}
        );
    }

    private List<String[]> prepareParagraphCase(CaseAbstracts abstracts) {
        return Arrays.asList(
                new String[]{"BACKGROUND: ", abstracts.getCaseReport()},
                new String[]{"CASE REPORT: ", abstracts.getBackground()},
                new String[]{"CONCLUSIONS: ", abstracts.getConclusions()}
        );
    }

    private List<String[]> prepareParagraphResearch(ResearchAbstracts abstracts) {
        return Arrays.asList(
                new String[]{"INTRODUCTION: ", abstracts.getIntroduction()},
                new String[]{"AIM OF THE STUDY: ", abstracts.getAimOfTheStudy()},
                new String[]{"MATERIALS AND METHODS: ", abstracts.getMaterialAndMethods()},
                new String[]{"RESULTS: ", abstracts.getResults()},
                new String[]{"CONCLUSIONS: ", abstracts.getConclusions()}
        );
    }

    private void writeParagraph(XWPFParagraph paragraph, String[] value) {
        XWPFRun paragraphRun = paragraph.createRun();
        paragraphRun.setText(value[0]);
        paragraphRun.setBold(true);
        if(value[0].contains("Title")) {
            paragraphRun.setUnderline(UnderlinePatterns.SINGLE);
        }
        paragraphRun = paragraph.createRun();
        paragraphRun.setText(value[1]);
        if(value[0].contains("Title")) {
            paragraphRun.setUnderline(UnderlinePatterns.SINGLE);
        }
    }

}
