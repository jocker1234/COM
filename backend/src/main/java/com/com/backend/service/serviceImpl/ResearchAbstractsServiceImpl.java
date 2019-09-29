package com.com.backend.service.serviceImpl;

import com.com.backend.dao.CategoryDao;
import com.com.backend.dao.ResearchAbstractsDao;
import com.com.backend.dto.request.ResearchAbstractsDtoRequest;
import com.com.backend.dto.response.ResearchAbstractsDtoResponse;
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
import com.com.backend.service.ResearchAbstractsService;
import com.com.backend.service.UsersService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResearchAbstractsServiceImpl extends AbstractsAbstractServiceImpl<ResearchAbstractsDtoRequest,
                                                                        ResearchAbstractsDtoResponse, ResearchAbstracts>
                                            implements ResearchAbstractsService {

    private ResearchAbstractsDao researchAbstractsDao;
    private ResearchAbstractsMapper researchAbstractsMapper;

    public ResearchAbstractsServiceImpl(UsersService usersService, CategoryDao categoryDao,
                                        AbstractsService abstractsService, ResearchAbstractsDao researchAbstractsDao,
                                        ResearchAbstractsMapper researchAbstractsMapper) {
        super(usersService, categoryDao, abstractsService);
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
        if(isNull(researchAbstracts.getIntroduction())){
            throw new WrongValueException(ExceptionType.WRONG_VALUE, Fields.INTRODUCTION);
        }
        if(isNull(researchAbstracts.getAimOfTheStudy())){
            throw new WrongValueException(ExceptionType.WRONG_VALUE, Fields.AIM_OF_THE_STUDY);
        }
        if(isNull(researchAbstracts.getMaterialAndMethods())){
            throw new WrongValueException(ExceptionType.WRONG_VALUE, Fields.MATERIAL_AND_METHODS);
        }
        if(isNull(researchAbstracts.getResults())){
            throw new WrongValueException(ExceptionType.WRONG_VALUE, Fields.RESULT);
        }
        if(isNull(researchAbstracts.getConclusions())){
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
    public ResearchAbstractsDtoResponse update(Long id, ResearchAbstractsDtoRequest ra) throws AppException {
        validAbstracts(ra);
        validFields(ra);
        Optional<ResearchAbstracts> researchAbstracts = researchAbstractsDao.findById(id);
        if(!researchAbstracts.isPresent()){
            throw new AbstractNotFoundException(ExceptionType.NOT_FOUND);
        }
        if(!researchAbstracts.get().getStatus().equals(Status.DO.getStatus())) {
            throw new AppException(ExceptionType.ABSTRACT_SENT);
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
        }).orElseGet(() -> {
            ResearchAbstracts abstracts = researchAbstractsMapper.dtoReqToModel(ra);
            abstracts.setCategory(categoryDao.getOne(ra.getCategoryId()));
            return researchAbstractsDao.save(abstracts);
        });
        return researchAbstractsMapper.modelToDtoRes(researchAbstracts.get());
    }

    @Override
    public int forwardForApproval(Long id) throws AppException {
        if(!researchAbstractsDao.getStatus(id).equals(Status.DO.getStatus()))
            throw new AppException(ExceptionType.WRONG_STATUS);
        return researchAbstractsDao.changeStatusCase(Status.FORWARDED.getStatus(), id);
    }

    @Override
    public int approved(Long id) throws AppException {
        if(!researchAbstractsDao.getStatus(id).equals(Status.FORWARDED.getStatus()))
            throw new AppException(ExceptionType.WRONG_STATUS);
        return researchAbstractsDao.changeStatusCase(Status.APPROVED.getStatus(), id);
    }

    @Override
    public int rejected(Long id) throws AppException {
        if(!researchAbstractsDao.getStatus(id).equals(Status.FORWARDED.getStatus()))
            throw new AppException(ExceptionType.WRONG_STATUS);
        return researchAbstractsDao.changeStatusCase(Status.REJECTED.getStatus(), id);
    }

    @Override
    public List<ResearchAbstractsDtoResponse> getAllAbstractsByUserEmail(String email) {
        List<ResearchAbstracts> caseAbstracts = researchAbstractsDao.getAllByUsersEmail(email);
        return researchAbstractsMapper.modelListToDtoListRes(caseAbstracts);
    }

}
