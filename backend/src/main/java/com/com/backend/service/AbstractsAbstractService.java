package com.com.backend.service;

import com.com.backend.dto.response.AbstractsDtoResponse;
import com.com.backend.model.Abstracts;
import com.com.backend.dto.request.AbstractsDtoRequest;
import com.com.backend.exception.AbstractNotFoundException;
import com.com.backend.exception.AppException;
import com.com.backend.exception.WrongValueException;

import java.util.List;

public interface AbstractsAbstractService <TREQ extends AbstractsDtoRequest, TRES extends AbstractsDtoResponse,
                                                                                                S extends Abstracts> {
    List<TRES> getAll();
    TRES create(TREQ t, String token) throws AppException;
    TRES update(Long id, TREQ t) throws AppException;

    void delete(Long id) throws AbstractNotFoundException;

    int forwardForApproval(Long id) throws AppException;

    TRES getOne(Long id) throws AbstractNotFoundException;

    int changeStatus(Long id, String status);
    int approved(Long id) throws AppException;
    int rejected(Long id) throws AppException;

    int countUserAbstract(String email);

    List<TRES> getAllAbstractsByUserEmail(String email);
}
