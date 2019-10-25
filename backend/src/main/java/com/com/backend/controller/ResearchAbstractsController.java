package com.com.backend.controller;

import com.com.backend.dto.request.ResearchAbstractsDtoRequest;
import com.com.backend.dto.response.ResearchAbstractsDtoResponse;
import com.com.backend.model.ResearchAbstracts;
import com.com.backend.service.AbstractsAbstractService;
import com.com.backend.service.ResearchAbstractsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(value = {"http://localhost:4200", "http://54.37.234.192:4200"})
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
