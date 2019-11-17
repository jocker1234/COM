package com.com.backend.service.serviceImpl;

import com.com.backend.dao.DirectoryDao;
import com.com.backend.dto.request.DirectoryDtoRequest;
import com.com.backend.dto.response.DirectoryDtoResponse;
import com.com.backend.exception.AccessException;
import com.com.backend.mapper.DirectoryMapper;
import com.com.backend.model.Directory;
import com.com.backend.model.enums.ExceptionType;
import com.com.backend.model.enums.Role;
import com.com.backend.service.AuthoritiesService;
import com.com.backend.service.DirectoryService;
import com.com.backend.service.UsersService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DirectoryServiceImpl implements DirectoryService {

    private DirectoryDao directoryDao;
    private DirectoryMapper directoryMapper;
    private AuthoritiesService authoritiesService;
    private UsersService usersService;

    public DirectoryServiceImpl(DirectoryDao directoryDao, DirectoryMapper directoryMapper,
                                                AuthoritiesService authoritiesService, UsersService usersService) {
        this.directoryDao = directoryDao;
        this.directoryMapper = directoryMapper;
        this.authoritiesService = authoritiesService;
        this.usersService = usersService;
    }

    public List<DirectoryDtoResponse> getAll(String token) throws AccessException {
        checkAuthorities(token);
        return directoryMapper.listToListDtoRes(directoryDao.findAll());
    }

    public DirectoryDtoResponse getOne(Long id, String token) throws AccessException {
        checkAuthorities(token);
        return directoryMapper.modelToDtoRes(directoryDao.findById(id).get());
    }

    public DirectoryDtoResponse updataParameter(String token, Long id, DirectoryDtoRequest directoryReq) throws AccessException {
        checkAuthorities(token);
        Optional<Directory> directory = directoryDao.findById(id);
        directory = Optional.of(updateFiledDirectory(directory.get(), directoryReq));
        Directory directorySave = directoryDao.save(directory.get());
        return directoryMapper.modelToDtoRes(directorySave);
    }

    private Directory updateFiledDirectory(Directory directory, DirectoryDtoRequest request) {
        if(!directory.getValue().equals(request.getValue())) {
            directory.setValue(request.getValue());
        }
        return directory;
    }

    private void checkAuthorities(String token) throws AccessException {
        String email = usersService.getEmailFromToken(token);
        List<String> authorities = authoritiesService.getUserAuthorities(email);
        if(!authorities.contains(Role.ROLE_ADMIN.name())) {
            throw new AccessException(ExceptionType.NO_ACCESS);
        }
    }

}
