package com.com.backend.controller;

import com.com.backend.dto.request.ResearchAbstractsDtoRequest;
import com.com.backend.dto.response.ResearchAbstractsDtoResponse;
import com.com.backend.model.ResearchAbstracts;
import com.com.backend.service.AbstractsAbstractService;
import com.com.backend.service.ResearchAbstractsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/abstracts/research")
public class ResearchAbstractsController extends AbstractsAbstractController<ResearchAbstractsDtoRequest,
                                                                    ResearchAbstractsDtoResponse, ResearchAbstracts> {

    private ResearchAbstractsService researchAbstractsService;

    public ResearchAbstractsController(ResearchAbstractsService researchAbstractsService) {
        this.researchAbstractsService = researchAbstractsService;
    }

    @Override
    public AbstractsAbstractService<ResearchAbstractsDtoRequest, ResearchAbstractsDtoResponse, ResearchAbstracts> getService() {
        return researchAbstractsService;
    }
}
