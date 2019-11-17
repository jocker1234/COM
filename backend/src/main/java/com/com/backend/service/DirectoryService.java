package com.com.backend.service;

import com.com.backend.dto.request.DirectoryDtoRequest;
import com.com.backend.dto.response.DirectoryDtoResponse;
import com.com.backend.exception.AccessException;
import com.com.backend.model.Directory;

public interface DirectoryService extends AbstractService<Directory, DirectoryDtoResponse> {

    DirectoryDtoResponse updataParameter(String token, Long id, DirectoryDtoRequest directoryReq) throws AccessException;

}
