package com.com.backend.controller;

import com.com.backend.dto.request.CaseAbstractsDtoRequest;
import com.com.backend.dto.response.CaseAbstractsDtoResponse;
import com.com.backend.model.CaseAbstracts;
import com.com.backend.service.AbstractsAbstractService;
import com.com.backend.service.CaseAbstractsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/abstracts/case")
public class CaseAbstractsController extends AbstractsAbstractController<CaseAbstractsDtoRequest,
                                                                            CaseAbstractsDtoResponse, CaseAbstracts> {

    private CaseAbstractsService caseAbstractsService;

    public CaseAbstractsController(CaseAbstractsService caseAbstractsService) {
        this.caseAbstractsService = caseAbstractsService;
    }

    @Override
    public AbstractsAbstractService<CaseAbstractsDtoRequest, CaseAbstractsDtoResponse, CaseAbstracts> getService() {
        return caseAbstractsService;
    }
}
