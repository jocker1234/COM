package com.com.backend.service.serviceImpl;

import com.com.backend.dao.CategoryDao;
import com.com.backend.dto.request.AbstractsDtoRequest;
import com.com.backend.dto.response.AbstractsDtoResponse;
import com.com.backend.exception.AbstractNotFoundException;
import com.com.backend.exception.AppException;
import com.com.backend.exception.WrongValueException;
import com.com.backend.mapper.AbstractsMapper;
import com.com.backend.model.Abstracts;
import com.com.backend.model.Users;
import com.com.backend.model.enums.*;
import com.com.backend.service.AbstractsAbstractService;
import com.com.backend.service.AbstractsService;
import com.com.backend.service.EmailService;
import com.com.backend.service.UsersService;
import com.com.backend.util.Util;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public abstract class AbstractsAbstractServiceImpl<TREQ extends AbstractsDtoRequest, TRES extends AbstractsDtoResponse,
        S extends Abstracts> implements AbstractsAbstractService<TREQ, TRES, S> {

    protected CategoryDao categoryDao;
    protected UsersService usersService;
    protected AbstractsService abstractsService;
    protected EmailService emailService;

    public AbstractsAbstractServiceImpl(CategoryDao categoryDao, UsersService usersService,
                                        AbstractsService abstractsService, EmailService emailService) {
        this.categoryDao = categoryDao;
        this.usersService = usersService;
        this.abstractsService = abstractsService;
        this.emailService = emailService;
    }

    public abstract AbstractsMapper getMapper();

    public abstract JpaRepository<S, Long> getDao();

    protected abstract void validFields(TREQ t) throws WrongValueException;

    protected abstract S dtoReqToModel(TREQ t);

    public List<TRES> getAll() {
        List<S> s = getDao().findAll();
        return getMapper().modelListToDtoListRes(s);
    }

    public TRES getOne(Long id, String token) throws AppException {
        long userId = usersService.getUserIDFromToken(token);
        Optional<S> s = getDao().findById(id);
        if (!s.isPresent()) {
            throw new AbstractNotFoundException(ExceptionType.NOT_FOUND);
        }
        S s1 = s.get();
        Users users = usersService.getUser(userId);
        if(s.get().getUsers().getId() != userId && !users.getAuthoritiesSet().stream().anyMatch(n -> n.getRoleName().equals(Role.ROLE_ADMIN))){
            throw new AppException(ExceptionType.NO_ACCESS);
        }
        return (TRES) getMapper().modelToDtoRes(s.get());
    }

    protected void validAbstracts(TREQ t) throws WrongValueException {
        if (t.getCategoryId() == null) {
            throw new WrongValueException(ExceptionType.WRONG_VALUE, Fields.CATEGORY);
        }
        if (t.getAuthors() == null || t.getAuthors().length == 0) {
            throw new WrongValueException(ExceptionType.WRONG_VALUE, Fields.AUTHOR);
        }
        if (Util.isNull(t.getTitle())) {
            throw new WrongValueException(ExceptionType.WRONG_VALUE, Fields.TITLE);
        }
        if (Util.isNull(t.getTutors())) {
            throw new WrongValueException(ExceptionType.WRONG_VALUE, Fields.TUTORS);
        }
        if (Util.isNull(t.getAffiliation())) {
            throw new WrongValueException(ExceptionType.WRONG_VALUE, Fields.AFFILIATION);
        }
        if (Util.isNull(t.getStatus())) {
            t.setStatus(Status.DO.getStatus());
        }
        t.setType(AbstractType.findType(t.getType()).name());
    }

    public TRES create(TREQ t, String token) throws AppException {
        String email = usersService.getEmailFromToken(token);
        long countAllAbstractUser = abstractsService.countAllAbstractUser(email);
        if (countAllAbstractUser >= 2) {
            throw new AppException(ExceptionType.ABSTRACT_AMMOUNT);
        }
        validAbstracts(t);
        validFields(t);
        S s = dtoReqToModel(t);
        s.setCategory(categoryDao.getOne(t.getCategoryId()));
        Users users = usersService.getUserByEmail(email);
        s.setUsers(users);
        S added = getDao().save(s);
        return (TRES) getMapper().modelToDtoRes(added);
    }

    @Transactional
    public TRES update(Long id, TREQ t, String token) throws AppException {
        S updated = getDao().save(dtoReqToModel(t));
        return (TRES) getMapper().modelToDtoRes(updated);
    }

    protected S setValue(S sTo, TREQ tFrom) {
        if (!sTo.getCategory().getId().equals(tFrom.getCategoryId())) {
            sTo.setCategory(categoryDao.getOne(tFrom.getCategoryId()));
        }
        if (!sTo.getAuthors().equals(tFrom.getAuthors())) {
            sTo.setAuthors(tFrom.getAuthors());
        }
        if (!sTo.getTitle().equals(tFrom.getTitle())) {
            sTo.setTitle(tFrom.getTitle());
        }
        if (!sTo.getTutors().equals(tFrom.getTutors())) {
            sTo.setTutors(tFrom.getTutors());
        }
        if (!sTo.getAffiliation().equals(tFrom.getAffiliation())) {
            sTo.setAffiliation(tFrom.getAffiliation());
        }
        return sTo;
    }

    @Transactional
    public void delete(Long id, String token) throws AppException {
        long userId = usersService.getUserIDFromToken(token);
        if(id != userId){
            throw new AppException(ExceptionType.NO_ACCESS);
        }
        try {
            getDao().deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new AbstractNotFoundException(ExceptionType.NOT_FOUND);
        }
    }
}
