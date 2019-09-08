package com.com.backend.mapper;

import com.com.backend.domain.Abstracts;
import com.com.backend.dto.AbstractsDto;

import java.util.List;

public interface AbstractsMapper<T extends AbstractsDto, S extends Abstracts> {

    List<T> dtoListToModelList(List<S> s);
    T dtoToModel(S s);
    S modelToDto(T t);

}
