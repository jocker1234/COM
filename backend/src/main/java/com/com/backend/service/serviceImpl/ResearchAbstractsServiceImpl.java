package com.com.backend.service.serviceImpl;

import com.com.backend.dao.CategoryDao;
import com.com.backend.dao.ResearchAbstractsDao;
import com.com.backend.dto.request.ResearchAbstractsDtoRequest;
import com.com.backend.dto.response.ResearchAbstractsDtoResponse;
import com.com.backend.model.Abstracts;
import com.com.backend.model.CaseAbstracts;
import com.com.backend.model.ResearchAbstracts;
import com.com.backend.model.enums.ExceptionType;
import com.com.backend.model.enums.Fields;
import com.com.backend.model.enums.Status;
import com.com.backend.exception.AbstractNotFoundException;
import com.com.backend.exception.AppException;
import com.com.backend.exception.WrongValueException;
import com.com.backend.mapper.AbstractsMapper;
import com.com.backend.mapper.ResearchAbstractsMapper;
import com.com.backend.service.AbstractsService;
import com.com.backend.service.EmailService;
import com.com.backend.service.ResearchAbstractsService;
import com.com.backend.service.UsersService;
import com.com.backend.util.Util;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ResearchAbstractsServiceImpl extends AbstractsAbstractServiceImpl<ResearchAbstractsDtoRequest,
                                    ResearchAbstractsDtoResponse, ResearchAbstracts> implements ResearchAbstractsService {

    private ResearchAbstractsDao researchAbstractsDao;
    private ResearchAbstractsMapper researchAbstractsMapper;

    public ResearchAbstractsServiceImpl(CategoryDao categoryDao, UsersService usersService, AbstractsService abstractsService,
                                        EmailService emailService, ResearchAbstractsDao researchAbstractsDao,
                                        ResearchAbstractsMapper researchAbstractsMapper) {
        super(categoryDao, usersService, abstractsService, emailService);
        this.researchAbstractsDao = researchAbstractsDao;
        this.researchAbstractsMapper = researchAbstractsMapper;
    }

    @Override
    public AbstractsMapper getMapper() {
        return researchAbstractsMapper;
    }

    @Override
    public JpaRepository<ResearchAbstracts, Long> getDao() {
        return researchAbstractsDao;
    }

    protected void validFields(ResearchAbstractsDtoRequest researchAbstracts) throws WrongValueException {
        if(Util.isNull(researchAbstracts.getIntroduction())){
            throw new WrongValueException(ExceptionType.WRONG_VALUE, Fields.INTRODUCTION);
        }
        if(Util.isNull(researchAbstracts.getAimOfTheStudy())){
            throw new WrongValueException(ExceptionType.WRONG_VALUE, Fields.AIM_OF_THE_STUDY);
        }
        if(Util.isNull(researchAbstracts.getMaterialAndMethods())){
            throw new WrongValueException(ExceptionType.WRONG_VALUE, Fields.MATERIAL_AND_METHODS);
        }
        if(Util.isNull(researchAbstracts.getResults())){
            throw new WrongValueException(ExceptionType.WRONG_VALUE, Fields.RESULT);
        }
        if(Util.isNull(researchAbstracts.getConclusions())){
            throw new WrongValueException(ExceptionType.WRONG_VALUE, Fields.CONCLUSION);
        }
    }

    @Override
    protected ResearchAbstracts dtoReqToModel(ResearchAbstractsDtoRequest researchAbstractsDto) {
        ResearchAbstracts abstracts = researchAbstractsMapper.dtoReqToModel(researchAbstractsDto);
        abstracts.setCategory(categoryDao.getOne(researchAbstractsDto.getCategoryId()));
        return abstracts;
    }

    @Override
    @Transactional
    public ResearchAbstractsDtoResponse update(Long id, ResearchAbstractsDtoRequest ra, String token) throws AppException {
        validAbstracts(ra);
        validFields(ra);
        long userId = usersService.getUserIDFromToken(token);
        Optional<ResearchAbstracts> researchAbstracts = researchAbstractsDao.findById(id);
        if(!researchAbstracts.isPresent()){
            throw new AbstractNotFoundException(ExceptionType.NOT_FOUND);
        }
        if(!researchAbstracts.get().getStatus().equals(Status.DO.getStatus())) {
            throw new AppException(ExceptionType.ABSTRACT_SENT);
        }
        if(researchAbstracts.get().getUsers().getId() != userId){
            throw new AppException(ExceptionType.NO_ACCESS);
        }
        researchAbstracts.map(thesis -> {
            thesis = setValue(thesis, ra);
            if (!thesis.getIntroduction().equals(ra.getIntroduction())) {
                thesis.setIntroduction(ra.getIntroduction());
            }
            if (!thesis.getAimOfTheStudy().equals(ra.getAimOfTheStudy())) {
                thesis.setAimOfTheStudy(ra.getAimOfTheStudy());
            }
            if (!thesis.getConclusions().equals(ra.getConclusions())) {
                thesis.setConclusions(ra.getConclusions());
            }
            if (!thesis.getResults().equals(ra.getResults())) {
                thesis.setResults(ra.getResults());
            }
            if (!thesis.getConclusions().equals(ra.getConclusions())) {
                thesis.setConclusions(ra.getConclusions());
            }
            return researchAbstractsDao.save(thesis);
        });
        return researchAbstractsMapper.modelToDtoRes(researchAbstracts.get());
    }

    @Override
    @Transactional
    public int forwardForApproval(Long id) throws AppException {
        if(!researchAbstractsDao.getStatus(id).equals(Status.DO.getStatus()))
            throw new AppException(ExceptionType.WRONG_STATUS);
        return researchAbstractsDao.changeStatusCase(Status.SEND.getStatus(), id);
    }

    @Override
    @Transactional
    public void changeStatus(Long id, String status) throws AppException {
        int result = researchAbstractsDao.changeStatusCase(status, id);
        if(result == 0)
            throw new AbstractNotFoundException(ExceptionType.NOT_FOUND);
        Abstracts abstracts = researchAbstractsDao.getOne(id);
        emailService.sendDecisionAboutAbstract(abstracts.getUsers(), abstracts);
    }

    @Override
    @Transactional
    public int approved(Long id) throws AppException {
        if(!researchAbstractsDao.getStatus(id).equals(Status.SEND.getStatus()))
            throw new AppException(ExceptionType.WRONG_STATUS);
        return researchAbstractsDao.changeStatusCase(Status.APPROVED.getStatus(), id);
    }

    @Override
    @Transactional
    public int rejected(Long id) throws AppException {
        if(!researchAbstractsDao.getStatus(id).equals(Status.SEND.getStatus()))
            throw new AppException(ExceptionType.WRONG_STATUS);
        return researchAbstractsDao.changeStatusCase(Status.REJECTED.getStatus(), id);
    }

    @Override
    public int countUserAbstract(String email) {
        List<String> statuses = Arrays.asList("A", "F");
        return researchAbstractsDao.countCaseAbstractsByUsersEmailAndStatusIn(email, statuses);
    }

    @Override
    public List<ResearchAbstractsDtoResponse> getAllAbstractsByUserEmail(String email) {
        List<ResearchAbstracts> caseAbstracts = researchAbstractsDao.getAllByUsersEmail(email);
        return researchAbstractsMapper.modelListToDtoListRes(caseAbstracts);
    }

    @Override
    public List<ResearchAbstractsDtoResponse> getAll(Map<String, String> allParams) {
        String t = "";
        if(!allParams.get("status").equals(""))
            t = Status.valueOf(allParams.getOrDefault("status", "").toUpperCase()).getStatus();
        String y = allParams.getOrDefault("typeAbstract", "").toUpperCase();
        String u = allParams.getOrDefault("nameCategory", "");

        List<ResearchAbstracts> abstracts = researchAbstractsDao.findAbstract(
                t,y,u
        );
        return researchAbstractsMapper.modelListToDtoListRes(abstracts);
    }
}
