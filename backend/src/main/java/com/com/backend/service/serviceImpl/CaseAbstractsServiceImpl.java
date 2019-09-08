package com.com.backend.service.serviceImpl;

import com.com.backend.dao.CaseAbstractsDao;
import com.com.backend.dao.CategoryDao;
import com.com.backend.domain.CaseAbstracts;
import com.com.backend.domain.enums.Errors;
import com.com.backend.domain.enums.Fields;
import com.com.backend.domain.enums.Status;
import com.com.backend.dto.CaseAbstractsDto;
import com.com.backend.exception.AbstractNotFoundException;
import com.com.backend.exception.AppException;
import com.com.backend.exception.WrongValueException;
import com.com.backend.mapper.AbstractsMapper;
import com.com.backend.mapper.CaseAbstractsMapper;
import com.com.backend.service.CaseAbstractsService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CaseAbstractsServiceImpl extends AbstractsServiceImpl<CaseAbstractsDto, CaseAbstracts> implements CaseAbstractsService {

    private CaseAbstractsDao caseAbstractsDao;
    private CaseAbstractsMapper caseAbstractsMapper;

    public CaseAbstractsServiceImpl(CaseAbstractsDao caseAbstractsDao, CategoryDao categoryDao, CaseAbstractsMapper caseAbstractsMapper) {
        super(categoryDao);
        this.caseAbstractsDao = caseAbstractsDao;
        this.caseAbstractsMapper = caseAbstractsMapper;
    }

    @Override
    public AbstractsMapper getMapper() {
        return caseAbstractsMapper;
    }

    @Override
    public JpaRepository<CaseAbstracts, Long> getDao() {
        return caseAbstractsDao;
    }

    @Override
    public CaseAbstracts modelToDto(CaseAbstractsDto caseAbstractsDto) {
        return caseAbstractsMapper.modelToDto(caseAbstractsDto);
    }

    public void validFields(CaseAbstractsDto caseAbstracts) throws WrongValueException {
        if (isNull(caseAbstracts.getCaseReport())) {
            throw new WrongValueException(Errors.WRONG_VALUE, Fields.CASE_REPORT);
        }
        if (isNull(caseAbstracts.getBackground())) {
            throw new WrongValueException(Errors.WRONG_VALUE, Fields.BACKGROUND);
        }
        if (isNull(caseAbstracts.getConclusions())) {
            throw new WrongValueException(Errors.WRONG_VALUE, Fields.CONCLUSION);
        }
    }

    @Override
    @Transactional
    public CaseAbstractsDto update(Long id, CaseAbstractsDto caseAbstracts) throws AppException {
        validAbstracts(caseAbstracts);
        validFields(caseAbstracts);
        Optional<CaseAbstracts> caseAbstract = caseAbstractsDao.findById(id);
        if(!caseAbstract.isPresent()){
            throw new AbstractNotFoundException(Errors.NOT_FOUND);
        }
        if(!caseAbstract.get().getStatus().equals(Status.DO.getStatus())){
            throw new AppException(Errors.ABSTRACT_SENT);
        }
        caseAbstract.map(thesis -> {
            thesis = setValue(thesis, caseAbstracts);
            if (!thesis.getCaseReport().equals(caseAbstracts.getCaseReport())) {
                thesis.setCaseReport(caseAbstracts.getCaseReport());
            }
            if (!thesis.getBackground().equals(caseAbstracts.getBackground())) {
                thesis.setBackground(caseAbstracts.getBackground());
            }
            if (!thesis.getConclusions().equals(caseAbstracts.getConclusions())) {
                thesis.setConclusions(caseAbstracts.getConclusions());
            }
            System.out.println("asdasdas");
            return caseAbstractsDao.save(thesis);
        }).orElseGet(() -> caseAbstractsDao.save(caseAbstractsMapper.modelToDto(caseAbstracts)));
        return caseAbstractsMapper.dtoToModel(caseAbstract.get());
    }

    @Override
    public int forwardForApproval(Long id) throws AppException {
        if(!caseAbstractsDao.getStatus(id).equals(Status.DO.getStatus()))
            throw new AppException(Errors.WRONG_STATUS);
        return caseAbstractsDao.changeStatusCase(Status.FORWARDED.getStatus(), id);
    }

    @Override
    public int approved(Long id) throws AppException {
        if(!caseAbstractsDao.getStatus(id).equals(Status.FORWARDED.getStatus()))
            throw new AppException(Errors.WRONG_STATUS);
        return caseAbstractsDao.changeStatusCase(Status.APPROVED.getStatus(), id);
    }

    @Override
    public int rejected(Long id) throws AppException {
        if(!caseAbstractsDao.getStatus(id).equals(Status.FORWARDED.getStatus()))
            throw new AppException(Errors.WRONG_STATUS);
        return caseAbstractsDao.changeStatusCase(Status.REJECTED.getStatus(), id);
    }

}
