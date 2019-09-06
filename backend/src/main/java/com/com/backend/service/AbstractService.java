package com.com.backend.service;

import com.com.backend.domain.AbstractEntity;
import com.com.backend.dto.AbstractDto;
import com.com.backend.exception.AppException;

import java.util.List;

public interface AbstractService<T extends AbstractEntity, TDTO extends AbstractDto> {

    TDTO getOne(Long id) throws AppException;

    List<TDTO> getAll();

}
