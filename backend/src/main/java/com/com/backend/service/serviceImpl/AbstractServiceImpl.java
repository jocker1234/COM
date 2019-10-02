package com.com.backend.service.serviceImpl;

import com.com.backend.config.security.JwtProvider;
import com.com.backend.model.Abstracts;
import com.com.backend.service.AbstractsAbstractService;
import com.com.backend.service.AbstractsService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AbstractServiceImpl implements AbstractsService {

    private JwtProvider jwtProvider;
    private AbstractsAbstractService researchAbstract;
    private AbstractsAbstractService caseAbstract;

    public AbstractServiceImpl(JwtProvider jwtProvider,
                               @Lazy @Qualifier("researchAbstractsServiceImpl") AbstractsAbstractService researchAbstract,
                               @Lazy @Qualifier("caseAbstractsServiceImpl") AbstractsAbstractService caseAbstract) {
        this.jwtProvider = jwtProvider;
        this.researchAbstract = researchAbstract;
        this.caseAbstract = caseAbstract;
    }

    public List<Abstracts> getAllAbstractUser(String token) {
        String email = getEmailFromToken(token);
        List<Abstracts> abstracts = new ArrayList<>();
        abstracts.addAll(caseAbstract.getAllAbstractsByUserEmail(email));
        abstracts.addAll(researchAbstract.getAllAbstractsByUserEmail(email));
        return abstracts;
    }

    public String getEmailFromToken(String token) {
        token = token.replaceFirst("Bearer ", "");
        return jwtProvider.getEmailFromJwtToken(token);
    }

    @Override
    public int countAllAbstractUser(String email) {
        int countCase = caseAbstract.countUserAbstract(email);
        int countResearch = researchAbstract.countUserAbstract(email);
        return countCase + countResearch;
    }

}
