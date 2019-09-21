package com.com.backend.controller;

import com.com.backend.model.CaseAbstracts;
import com.com.backend.dto.CaseAbstractsDto;
import com.com.backend.service.AbstractsService;
import com.com.backend.service.CaseAbstractsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/case")
public class CaseAbstractsController extends AbstractsController<CaseAbstractsDto, CaseAbstracts> {

    private CaseAbstractsService caseAbstractsService;

    public CaseAbstractsController(CaseAbstractsService caseAbstractsService) {
        this.caseAbstractsService = caseAbstractsService;
    }

    @Override
    public AbstractsService<CaseAbstractsDto, CaseAbstracts> getService() {
        return caseAbstractsService;
    }
}
