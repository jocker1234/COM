package com.com.backend.service.serviceImpl;

import com.com.backend.util.Util;
import com.com.backend.dao.CategoryDao;
import com.com.backend.model.Abstracts;
import com.com.backend.model.enums.ExceptionType;
import com.com.backend.model.enums.Fields;
import com.com.backend.model.enums.Status;
import com.com.backend.dto.AbstractsDto;
import com.com.backend.exception.AbstractNotFoundException;
import com.com.backend.exception.AppException;
import com.com.backend.exception.WrongValueException;
import com.com.backend.mapper.AbstractsMapper;
import com.com.backend.service.AbstractsService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public abstract class AbstractsServiceImpl<T extends AbstractsDto, S extends Abstracts> implements AbstractsService<T,S> {

    public abstract AbstractsMapper getMapper();
    public abstract JpaRepository<S, Long> getDao();
    protected abstract void validFields(T t) throws WrongValueException;
    protected abstract S modelToDto(T t);

    private CategoryDao categoryDao;

    public AbstractsServiceImpl(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    protected boolean isNull(String value){
        return !(value!=null && !value.isEmpty());
    }

    public List<T> getAll() {
        List<S> s = getDao().findAll();
        return getMapper().dtoListToModelList(s);
    }

    public T getOne(Long id) throws AbstractNotFoundException {
        Optional<S> s = getDao().findById(id);
        if(!s.isPresent()){
            throw new AbstractNotFoundException(ExceptionType.NOT_FOUND);
        }
        return (T) getMapper().dtoToModel(s.get());
    }

    protected void validAbstracts(T t) throws WrongValueException {
        if(t.getCategoryId()==null){
            throw new WrongValueException(ExceptionType.WRONG_VALUE, Fields.CATEGORY);
        }
        if(t.getAuthors()==null||t.getAuthors().size()==0){
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

    public T create(T t) throws WrongValueException {
        validAbstracts(t);
        validFields(t);
        t.setAuthors(t.getAuthors());
        S s = modelToDto(t);
        s.setCategory(categoryDao.getOne(t.getCategoryId()));
        S added = getDao().save(s);
        return (T) getMapper().dtoToModel(added);
    }

    @Transactional
    public T update(Long id, T t) throws AppException {
        S updated = getDao().save(modelToDto(t));
        return (T) getMapper().dtoToModel(updated);
    }

    protected S setValue(S sTo, T tFrom){
        if (!sTo.getCategory().getId().equals(tFrom.getCategoryId())) {
            sTo.setCategory(categoryDao.getOne(tFrom.getCategoryId()));
        }
        String rAuthors = Util.joinWithComma(tFrom.getAuthors());
        if (!sTo.getAuthors().equals(rAuthors)) {
            sTo.setAuthors(rAuthors);
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
