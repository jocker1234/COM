package com.com.backend.service;

import com.com.backend.model.Abstracts;

import java.util.List;

public interface AbstractsService {

    List<Abstracts> getAllAbstractUser(String token);
    String getEmailFromToken(String token);

}
