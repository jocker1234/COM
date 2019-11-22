package com.com.backend.service;

import com.com.backend.dto.request.DictionaryDtoRequest;
import com.com.backend.dto.response.DictionaryDtoResponse;
import com.com.backend.exception.AccessException;
import com.com.backend.model.Dictionary;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface DictionaryService extends AbstractService<Dictionary, DictionaryDtoResponse> {

    DictionaryDtoResponse updataParameter(String token, Long id, DictionaryDtoRequest directoryReq) throws AccessException;

    String getValueByKey(String key);

    DictionaryDtoResponse setImage(String token, Long id, MultipartFile file) throws IOException;
}
