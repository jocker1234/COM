package com.com.backend.service.serviceImpl;

import com.com.backend.config.security.JwtProvider;
import com.com.backend.dao.UsersDao;
import com.com.backend.dto.UsersDto;
import com.com.backend.exception.AppException;
import com.com.backend.exception.NotFoundException;
import com.com.backend.mapper.UserMapper;
import com.com.backend.model.Authorities;
import com.com.backend.model.Users;
import com.com.backend.model.enums.*;
import com.com.backend.service.AuthoritiesService;
import com.com.backend.service.EmailService;
import com.com.backend.service.UsersService;
import com.com.backend.util.Util;
import com.com.backend.validation.Validation;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class UsersServiceImpl implements UsersService {

    private JwtProvider jwtProvider;
    private UserMapper usersMapper;
    private UsersDao usersDao;
    private AuthoritiesService authoritiesService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private EmailService emailService;

    public UsersServiceImpl(JwtProvider jwtProvider, UserMapper usersMapper, UsersDao usersDao,
                            AuthoritiesService authoritiesService, BCryptPasswordEncoder bCryptPasswordEncoder,
                            EmailService emailService) {
        this.jwtProvider = jwtProvider;
        this.usersMapper = usersMapper;
        this.usersDao = usersDao;
        this.authoritiesService = authoritiesService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.emailService = emailService;
    }

    public Users usersDtoToUsers(UsersDto dto) {
        return usersMapper.usersDtoToUsers(dto);
    }

    public UsersDto usersToUsersDto(Users dto) {
        return usersMapper.usersToUsersDto(dto);
    }

    public String getEmailFromToken(String token) {
        token = token.replaceFirst("Bearer ", "");
        return jwtProvider.getEmailFromJwtToken(token);
    }

    @Override
    public Users findByEmail(String email) {
        return usersDao.findByEmail(email).orElse(null);
    }

    private void validateUser(UsersDto userDto) throws AppException {
        if (!Validation.emailValidation(userDto.getEmail()))
            throw new AppException(ExceptionType.EMAIL_FORMAT);

        if (Gender.valueOf(userDto.getGender()) == null)
            throw new AppException(EntityType.GENDER, ExceptionType.NOT_FOUND);

        if (!Validation.countryValidation(userDto.getCountry()))
            throw new AppException(EntityType.COUNTRY, ExceptionType.NOT_FOUND);

        if (Title.valueOf(userDto.getTitle()) == null)
            throw new AppException(EntityType.TITLE, ExceptionType.NOT_FOUND);

        if (YearOfStudy.valueOf(userDto.getYearOfStudy()) == null)
            throw new AppException(EntityType.YEAR_OF_STUDY, ExceptionType.NOT_FOUND);

        if (userDto.getNeedVisa() == null)
            throw new AppException(ExceptionType.NEED_VISA);

        if (userDto.getNeedVisa() && !Validation.passportNumberValidation(userDto.getPassportNumber()))
            throw new AppException(ExceptionType.PASSPORT_NUMBER_FORMAT);
    }

    private UsersDto validateAuthenticate(UsersDto userDto) throws AppException {
        userDto.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));

        if (userDto.getAuthorities() == null)
            throw new AppException(ExceptionType.EMAIL_FORMAT);
        if (userDto.getAuthorities() != null && userDto.getAuthorities().length != 0) {
            Set<Authorities> authoritiesDtos = new HashSet<>();

            Arrays.stream(userDto.getAuthorities()).forEach(role -> {
                switch (role.toLowerCase()) {
                    case "admin":
                    case "role admin":
                    case "role_admin":
                        Authorities authoritiesAdmin = authoritiesService.findByRole(Role.ROLE_ADMIN);
                        authoritiesDtos.add(authoritiesAdmin);
                        break;
                    case "active":
                    case "activate participant":
                    case "role_active_participant":
                        Authorities authoritiesUser = authoritiesService.findByRole(Role.ROLE_ACTIVE_PARTICIPANT);
                        authoritiesDtos.add(authoritiesUser);
                        break;
                    case "pasive":
                    case "pasive participant":
                    case "role_pasive_participant":
                        Authorities authoritiesPM = authoritiesService.findByRole(Role.ROLE_PASSIVE_PARTICIPANT);
                        authoritiesDtos.add(authoritiesPM);
                        break;
                }
            });
            userDto.setAuthoritiesSet(authoritiesService.dtosToEntities(authoritiesDtos));
        }
        return userDto;
    }

    @Override
    public UsersDto signUpUser(UsersDto usersDtoRequest) throws AppException {
        UsersDto userDto = usersDtoRequest;

        userDto = validateAuthenticate(userDto);
        validateUser(userDto);

        usersDao.save(usersDtoToUsers(userDto));
        emailService.sendCreateEmail(userDto);
        return userDto;
    }

    public boolean existsUserByEmail(String email) {
        return usersDao.existsByEmail(email);
    }

    @Override
    public Long getUserIdByEmail(String email) throws NotFoundException {
        Long userId = usersDao.getUserIdByEmail(email);
        if(Util.isNull(userId))
            throw new NotFoundException(EntityType.USER, ExceptionType.NOT_FOUND);
        return userId;
    }

    @Override
    public Users getUserByEmail(String email) throws NotFoundException {
        Users users = usersDao.getUsersByEmail(email);
        if (Util.isNull(users))
            throw new NotFoundException(EntityType.USER, ExceptionType.NOT_FOUND);
        return users;
    }

    @Override
    @Transactional
    public UsersDto updateUser(UsersDto userDto) throws AppException {
        validateUser(userDto);
        return usersToUsersDto(usersDao.save(usersDtoToUsers(userDto)));
    }

    @Override
    @Transactional
    public void deleteUser(Long id) throws AppException {
        getOne(id);
        usersDao.deleteById(id);
    }

    @Override
    public UsersDto getOne(Long id) throws AppException {
        UsersDto user = usersMapper.usersToUsersDto(usersDao.getOne(id));
        if (user == null)
            throw new AppException(EntityType.USER, ExceptionType.NOT_FOUND);
        return user;
    }

    @Override
    public List<UsersDto> getAll() {
        return usersMapper.usersDtosToUserss(usersDao.findAll());
    }

}
