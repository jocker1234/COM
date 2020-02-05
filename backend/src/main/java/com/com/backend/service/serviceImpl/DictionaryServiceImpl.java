package com.com.backend.service.serviceImpl;

import com.com.backend.dao.DictionaryDao;
import com.com.backend.dto.request.DictionaryDtoRequest;
import com.com.backend.dto.response.DictionaryDtoResponse;
import com.com.backend.exception.AccessException;
import com.com.backend.mapper.DictionaryMapper;
import com.com.backend.model.Dictionary;
import com.com.backend.service.AuthoritiesService;
import com.com.backend.service.DictionaryService;
import com.com.backend.service.UsersService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class DictionaryServiceImpl implements DictionaryService {

    private DictionaryDao dictionaryDao;
    private DictionaryMapper dictionaryMapper;
    private AuthoritiesService authoritiesService;
    private UsersService usersService;

    public DictionaryServiceImpl(DictionaryDao dictionaryDao, DictionaryMapper dictionaryMapper,
                                                AuthoritiesService authoritiesService, UsersService usersService) {
        this.dictionaryDao = dictionaryDao;
        this.dictionaryMapper = dictionaryMapper;
        this.authoritiesService = authoritiesService;
        this.usersService = usersService;
    }

    public List<DictionaryDtoResponse> getAll(String token) throws AccessException {
        usersService.checkIfAdmin(token);
        return dictionaryMapper.listToListDtoRes(dictionaryDao.findAll());
    }

    public DictionaryDtoResponse getOne(Long id, String token) throws AccessException {
        usersService.checkIfAdmin(token);
        Dictionary dictionary = dictionaryDao.findById(id).get();
        DictionaryDtoResponse dictionaryDtoResponse = dictionaryMapper.modelToDtoRes(dictionary);
        if(dictionary.getImage()!=null) {
            dictionaryDtoResponse.setImage(Base64.getEncoder().withoutPadding().encodeToString(dictionary.getImage()));
        }
        return dictionaryDtoResponse;
    }

    public DictionaryDtoResponse updataParameter(String token, Long id, DictionaryDtoRequest directoryReq) throws AccessException {
        usersService.checkIfAdmin(token);
        Optional<Dictionary> directory = dictionaryDao.findById(id);
        directory = Optional.of(updateFieldDirectory(directory.get(), directoryReq));
        Dictionary directorySave = dictionaryDao.save(directory.get());
        return dictionaryMapper.modelToDtoRes(directorySave);
    }

    @Override
    public String getValueByKey(String key) {
        Dictionary dictionary =  dictionaryDao.findByKey(key);
        String value = null;
        if((dictionary.getValue()==null&&dictionary.getImage()==null)||dictionary.getValue()!=null) {
            value = dictionary.getValue();
        }else if(dictionary.getValue()==null&&dictionary.getImage()!=null) {
            value = Base64.getEncoder().withoutPadding().encodeToString(dictionary.getImage());
        }
        return value;
    }

    private Dictionary updateFieldDirectory(Dictionary directory, DictionaryDtoRequest request) {
        if(directory.getValue() == null || !directory.getValue().equals(request.getValue())) {
            directory.setValue(request.getValue());
        }
        return directory;
    }



    public DictionaryDtoResponse setImage(String token, Long id, MultipartFile file) throws IOException {
        Dictionary dictionary = dictionaryDao.getOne(id);
        dictionary.setImage(file.getBytes());
        dictionaryDao.save(dictionary);
        return dictionaryMapper.modelToDtoRes(dictionary);
    }

}
