package com.com.backend.service.serviceImpl;

import com.com.backend.dao.CategoryDao;
import com.com.backend.dao.ResearchAbstractsDao;
import com.com.backend.model.ResearchAbstracts;
import com.com.backend.model.enums.ExceptionType;
import com.com.backend.model.enums.Fields;
import com.com.backend.model.enums.Status;
import com.com.backend.dto.ResearchAbstractsDto;
import com.com.backend.exception.AbstractNotFoundException;
import com.com.backend.exception.AppException;
import com.com.backend.exception.WrongValueException;
import com.com.backend.mapper.AbstractsMapper;
import com.com.backend.mapper.ResearchAbstractsMapper;
import com.com.backend.service.ResearchAbstractsService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ResearchAbstractsServiceImpl extends AbstractsServiceImpl<ResearchAbstractsDto, ResearchAbstracts> implements ResearchAbstractsService {

    private ResearchAbstractsDao researchAbstractsDao;
    private ResearchAbstractsMapper researchAbstractsMapper;

    public ResearchAbstractsServiceImpl(CategoryDao categoryDao, ResearchAbstractsDao researchAbstractsDao, ResearchAbstractsMapper researchAbstractsMapper) {
        super(categoryDao);
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

    protected void validFields(ResearchAbstractsDto researchAbstracts) throws WrongValueException {
        if(isNull(researchAbstracts.getIntrodution())){
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
    protected ResearchAbstracts modelToDto(ResearchAbstractsDto researchAbstractsDto) {
        return researchAbstractsMapper.modelToDto(researchAbstractsDto);
    }

    @Override
    public ResearchAbstractsDto update(Long id, ResearchAbstractsDto ra) throws AppException {
        validAbstracts(ra);
        validFields(ra);
        Optional<ResearchAbstracts> researchAbstracts = researchAbstractsDao.findById(id);
        if(!researchAbstracts.isPresent()){
            throw new AbstractNotFoundException(ExceptionType.NOT_FOUND);
        }
        if(!researchAbstracts.get().getStatus().equals(Status.DO)) {
            throw new AppException(ExceptionType.ABSTRACT_SENT);
        }
        researchAbstracts.map(thesis -> {
            thesis = setValue(thesis, ra);
            if (!thesis.getIntrodution().equals(ra.getIntrodution())) {
                thesis.setIntrodution(ra.getIntrodution());
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
        }).orElseGet(() -> researchAbstractsDao.save(researchAbstractsMapper.modelToDto(ra)));
        return researchAbstractsMapper.dtoToModel(researchAbstracts.get());
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
}
