package com.com.backend.service;

import com.com.backend.model.Abstracts;
import com.com.backend.dto.AbstractsDto;
import com.com.backend.exception.AbstractNotFoundException;
import com.com.backend.exception.AppException;
import com.com.backend.exception.WrongValueException;

import java.util.List;

public interface AbstractsService <T extends AbstractsDto, S extends Abstracts> {
    List<T> getAll();
    T create(T t) throws WrongValueException;
    T update(Long id, T t) throws AppException;

    void delete(Long id) throws AbstractNotFoundException;

    int forwardForApproval(Long id) throws AppException;

    T getOne(Long id) throws AbstractNotFoundException;

    int approved(Long id) throws AppException;
    int rejected(Long id) throws AppException;
}
