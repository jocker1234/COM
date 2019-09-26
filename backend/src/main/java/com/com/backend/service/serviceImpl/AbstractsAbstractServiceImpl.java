package com.com.backend.service.serviceImpl;

import com.com.backend.dto.request.AbstractsDtoRequest;
import com.com.backend.dto.response.AbstractsDtoResponse;
import com.com.backend.model.Users;
import com.com.backend.service.AbstractsService;
import com.com.backend.service.UsersService;
import com.com.backend.dao.CategoryDao;
import com.com.backend.model.Abstracts;
import com.com.backend.model.enums.ExceptionType;
import com.com.backend.model.enums.Fields;
import com.com.backend.model.enums.Status;
import com.com.backend.exception.AbstractNotFoundException;
import com.com.backend.exception.AppException;
import com.com.backend.exception.WrongValueException;
import com.com.backend.mapper.AbstractsMapper;
import com.com.backend.service.AbstractsAbstractService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public abstract class AbstractsAbstractServiceImpl<TREQ extends AbstractsDtoRequest, TRES extends AbstractsDtoResponse,
                                                S extends Abstracts> implements AbstractsAbstractService<TREQ, TRES, S> {

    public abstract AbstractsMapper getMapper();
    public abstract JpaRepository<S, Long> getDao();
    protected abstract void validFields(TREQ t) throws WrongValueException;
    protected abstract S dtoReqToModel(TREQ t);

    private UsersService usersService;
    protected CategoryDao categoryDao;
    private AbstractsService abstractsService;

    public AbstractsAbstractServiceImpl(UsersService usersService, CategoryDao categoryDao, AbstractsService abstractsService) {
        this.usersService = usersService;
        this.categoryDao = categoryDao;
        this.abstractsService = abstractsService;
    }

    protected boolean isNull(String value){
        return !(value!=null && !value.isEmpty());
    }

    public List<TRES> getAll() {
        List<S> s = getDao().findAll();
        return getMapper().modelListToDtoListRes(s);
    }

    public TRES getOne(Long id) throws AbstractNotFoundException {
        Optional<S> s = getDao().findById(id);
        if(!s.isPresent()){
            throw new AbstractNotFoundException(ExceptionType.NOT_FOUND);
        }
        return (TRES) getMapper().modelToDtoRes(s.get());
    }

    protected void validAbstracts(TREQ t) throws WrongValueException {
        if(t.getCategoryId()==null){
            throw new WrongValueException(ExceptionType.WRONG_VALUE, Fields.CATEGORY);
        }
        if(t.getAuthors()==null||t.getAuthors().length==0){
            throw new WrongValueException(ExceptionType.WRONG_VALUE, Fields.AUTHOR);
        }
        if(isNull(t.getTitle())){
            throw new WrongValueException(ExceptionType.WRONG_VALUE, Fields.TITLE);
        }
        if(isNull(t.getTutors())){
            throw new WrongValueException(ExceptionType.WRONG_VALUE, Fields.TUTORS);
        }
        if(isNull(t.getStatus())){
            t.setStatus(Status.DO.getStatus());
        }
    }

    public TRES create(TREQ t, String token) throws WrongValueException {
        validAbstracts(t);
        validFields(t);
        S s = dtoReqToModel(t);
        s.setCategory(categoryDao.getOne(t.getCategoryId()));
        String email = abstractsService.getEmailFromToken(token);
        Users users = usersService.getUserByEmail(email);
        s.setUsers(users);
        S added = getDao().save(s);
        return (TRES) getMapper().modelToDtoRes(added);
    }

    @Transactional
    public TRES update(Long id, TREQ t) throws AppException {
        S updated = getDao().save(dtoReqToModel(t));
        return (TRES) getMapper().modelToDtoRes(updated);
    }

    protected S setValue(S sTo, TREQ tFrom){
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
        return sTo;
    }

    @Transactional
    public void delete(Long id) throws AbstractNotFoundException {
        try{
            getDao().deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw new AbstractNotFoundException(ExceptionType.NOT_FOUND);
        }
    }
}
