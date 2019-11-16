package com.com.backend.service;

import com.com.backend.exception.AppException;
import com.com.backend.model.Abstracts;

import java.util.List;

public interface AbstractsService {

    List<Abstracts> getAllAbstractUser(String token);
    long countAllAbstractUser(String email) throws AppException;

}
