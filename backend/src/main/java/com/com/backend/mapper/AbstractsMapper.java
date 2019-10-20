package com.com.backend.mapper;

import com.com.backend.dto.request.AbstractsDtoRequest;
import com.com.backend.dto.response.AbstractsDtoResponse;
import com.com.backend.model.Abstracts;
import org.mapstruct.*;

import java.util.List;

public interface AbstractsMapper<TREQ extends  AbstractsDtoRequest, TRES extends AbstractsDtoResponse, S extends Abstracts> {

    List<TREQ> modelListToDtoListReq(List<S> s);
    List<TRES> modelListToDtoListRes(List<S> s);
    S dtoReqToModel(TREQ t);
    S dtoResToModel(TRES t);
    TREQ modelToDtoReq(S t);
    TRES modelToDtoRes(S t);

}
