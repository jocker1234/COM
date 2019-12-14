package com.com.backend.service.serviceImpl;

import com.com.backend.dao.CaseAbstractsDao;
import com.com.backend.dao.CategoryDao;
import com.com.backend.dto.request.CaseAbstractsDtoRequest;
import com.com.backend.dto.response.CaseAbstractsDtoResponse;
import com.com.backend.exception.AbstractNotFoundException;
import com.com.backend.exception.AccessException;
import com.com.backend.exception.AppException;
import com.com.backend.exception.WrongValueException;
import com.com.backend.mapper.AbstractsMapper;
import com.com.backend.mapper.CaseAbstractsMapper;
import com.com.backend.model.Abstracts;
import com.com.backend.model.CaseAbstracts;
import com.com.backend.model.Users;
import com.com.backend.model.enums.ExceptionType;
import com.com.backend.model.enums.Fields;
import com.com.backend.model.enums.Status;
import com.com.backend.service.AbstractsService;
import com.com.backend.service.CaseAbstractsService;
import com.com.backend.service.EmailService;
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
public class CaseAbstractsServiceImpl extends AbstractsAbstractServiceImpl<CaseAbstractsDtoRequest,
        CaseAbstractsDtoResponse, CaseAbstracts> implements CaseAbstractsService {

    private CaseAbstractsDao caseAbstractsDao;
    private CaseAbstractsMapper caseAbstractsMapper;

    public CaseAbstractsServiceImpl(CategoryDao categoryDao, UsersService usersService, AbstractsService abstractsService,
                                    EmailService emailService, CaseAbstractsDao caseAbstractsDao,
                                    CaseAbstractsMapper caseAbstractsMapper) {
        super(categoryDao, usersService, abstractsService, emailService);
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
    public CaseAbstracts dtoReqToModel(CaseAbstractsDtoRequest caseAbstractsDto) {
        CaseAbstracts abstracts = caseAbstractsMapper.dtoReqToModel(caseAbstractsDto);
        abstracts.setCategory(categoryDao.getOne(caseAbstractsDto.getCategoryId()));
        return abstracts;
    }

    public void validFields(CaseAbstractsDtoRequest caseAbstracts) throws WrongValueException {
        if (Util.isNull(caseAbstracts.getCaseReport())) {
            throw new WrongValueException(ExceptionType.WRONG_VALUE, Fields.CASE_REPORT);
        }
        if (Util.isNull(caseAbstracts.getBackground())) {
            throw new WrongValueException(ExceptionType.WRONG_VALUE, Fields.BACKGROUND);
        }
        if (Util.isNull(caseAbstracts.getConclusions())) {
            throw new WrongValueException(ExceptionType.WRONG_VALUE, Fields.CONCLUSION);
        }
    }

    @Override
    @Transactional
    public CaseAbstractsDtoResponse update(Long id, CaseAbstractsDtoRequest caseAbstracts, String token) throws AppException {
        validAbstracts(caseAbstracts);
        validFields(caseAbstracts);
        long userId = usersService.getUserIDFromToken(token);
        Optional<CaseAbstracts> caseAbstract = caseAbstractsDao.findById(id);
        if (!caseAbstract.isPresent()) {
            throw new AbstractNotFoundException(ExceptionType.NOT_FOUND);
        }
        if (!caseAbstract.get().getStatus().equals(Status.DO.getStatus())) {
            throw new AppException(ExceptionType.ABSTRACT_SENT);
        }
        if(caseAbstract.get().getUsers().getId() != userId){
            throw new AppException(ExceptionType.NO_ACCESS);
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
            return caseAbstractsDao.save(thesis);
        });
        return caseAbstractsMapper.modelToDtoRes(caseAbstract.get());
    }

    @Override
    @Transactional
    public int forwardForApproval(Long id) throws AppException {
        if (!caseAbstractsDao.getStatus(id).equals(Status.DO.getStatus()))
            throw new AppException(ExceptionType.WRONG_STATUS);
        return caseAbstractsDao.changeStatusCase(Status.SEND.getStatus(), id);
    }

    @Override
    @Transactional
    public void changeStatus(Long id, String status) throws AppException {
        int result = caseAbstractsDao.changeStatusCase(status, id);
        if(result == 0)
            throw new AbstractNotFoundException(ExceptionType.NOT_FOUND);
        Abstracts abstracts = caseAbstractsDao.getOne(id);
        emailService.sendDecisionAboutAbstract(abstracts.getUsers(), abstracts);
    }

    @Override
    @Transactional
    public int approved(Long id) throws AppException {
        if (!caseAbstractsDao.getStatus(id).equals(Status.SEND.getStatus()))
            throw new AppException(ExceptionType.WRONG_STATUS);
        return caseAbstractsDao.changeStatusCase(Status.APPROVED.getStatus(), id);
    }

    @Override
    @Transactional
    public int rejected(Long id) throws AppException {
        if (!caseAbstractsDao.getStatus(id).equals(Status.SEND.getStatus()))
            throw new AppException(ExceptionType.WRONG_STATUS);
        return caseAbstractsDao.changeStatusCase(Status.REJECTED.getStatus(), id);
    }

    @Override
    public int countUserAbstract(String email) {
        List<String> statuses = Arrays.asList("A", "F");
        return caseAbstractsDao.countCaseAbstractsByUsersEmailAndStatusIn(email, statuses);
    }

    @Override
    public List<CaseAbstractsDtoResponse> getAllAbstractsByUserEmail(String email) {
        List<CaseAbstracts> caseAbstracts = caseAbstractsDao.getAllByUsersEmail(email);
        return caseAbstractsMapper.modelListToDtoListRes(caseAbstracts);
    }

    @Override
    public void deleteAllAbstracts(String token) throws AccessException {
        usersService.checkIfAdmin(token);
        caseAbstractsDao.deleteAll();
    }

    @Override
    public List<CaseAbstractsDtoResponse> getAll(Map<String, String> allParams) {
        String t = "";
        if(!allParams.get("status").equals(""))
            t = Status.valueOf(allParams.getOrDefault("status", "").toUpperCase()).getStatus();
        String y = allParams.getOrDefault("typeAbstract", "").toUpperCase();
        String u = allParams.getOrDefault("nameCategory", "");

        List<CaseAbstracts> abstracts = caseAbstractsDao.findAbstract(
                t,y,u
        );
        return caseAbstractsMapper.modelListToDtoListRes(abstracts);
    }
}
