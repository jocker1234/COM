package com.com.backend.service;

import com.com.backend.exception.AccessException;
import com.com.backend.model.AbstractEntity;
import com.com.backend.dto.AbstractDto;
import com.com.backend.exception.AppException;

import java.util.List;

public interface AbstractService<T extends AbstractEntity, TDTO extends AbstractDto> {

    TDTO getOne(Long id, String token) throws AppException;

    List<TDTO> getAll(String token) throws AccessException;

}
