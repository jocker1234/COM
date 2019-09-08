package com.com.backend.controller;

import com.com.backend.domain.ResearchAbstracts;
import com.com.backend.dto.ResearchAbstractsDto;
import com.com.backend.service.AbstractsService;
import com.com.backend.service.ResearchAbstractsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/research")
public class ResearchAbstractsController extends AbstractsController<ResearchAbstractsDto, ResearchAbstracts> {

    private ResearchAbstractsService researchAbstractsService;

    public ResearchAbstractsController(ResearchAbstractsService researchAbstractsService) {
        this.researchAbstractsService = researchAbstractsService;
    }

    @Override
    public AbstractsService<ResearchAbstractsDto, ResearchAbstracts> getService() {
        return researchAbstractsService;
    }
}
