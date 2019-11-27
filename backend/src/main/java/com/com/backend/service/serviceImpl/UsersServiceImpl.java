package com.com.backend.service.serviceImpl;

import com.com.backend.config.security.JwtProvider;
import com.com.backend.dao.UsersDao;
import com.com.backend.dto.request.UserAdminDtoRequest;
import com.com.backend.dto.request.UserCreateRequest;
import com.com.backend.dto.request.UserRequest;
import com.com.backend.dto.request.UserUpdateRequest;
import com.com.backend.dto.response.UserAdminDtoResponse;
import com.com.backend.dto.response.UserResponse;
import com.com.backend.dto.response.UserResponseWithAbstracts;
import com.com.backend.exception.AccessException;
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

import java.util.*;

@Service
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

    private Users usersDtoToUsers(UserCreateRequest dto) {
        return usersMapper.usersDtoToUsers(dto);
    }

    public String getEmailFromToken(String token) {
        token = token.replaceFirst("Bearer ", "");
        return jwtProvider.getEmailFromJwtToken(token);
    }

    public Long getUserIDFromToken(String token) throws NotFoundException {
        return getUserIdByEmail(getEmailFromToken(token));
    }

    public String getEmailFromUserId(long id) {
        return usersDao.getEmailById(id);
    }

    @Override
    public Users findByEmail(String email) {
        return usersDao.findByEmail(email).orElse(null);
    }

    private void validateUser(UserRequest userDto) throws AppException {
        if (!Validation.emailValidation(userDto.getEmail()))
            throw new AppException(ExceptionType.EMAIL_FORMAT);

        //if (Gender.valueOf(userDto.getGender()) == null)
        //    throw new AppException(EntityType.GENDER, ExceptionType.NOT_FOUND);

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

    private UserCreateRequest validateAuthenticate(UserCreateRequest userDto) throws AppException {

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
    @Transactional
    public UserResponse signUpUser(UserCreateRequest usersDtoRequest) throws AppException {

        UserCreateRequest userDto = usersDtoRequest;

        userDto = validateAuthenticate(userDto);
        validateUser(userDto);

        Users users = usersDao.save(usersDtoToUsers(userDto));
        emailService.sendCreateEmail(users);
        return usersMapper.usersToUsersResponse(users);
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

    public Users setResetToken(Users users) {
        users.setResetToken(UUID.randomUUID().toString());
        users = usersDao.save(users);
        return users;
    }

    @Transactional
    public void changePassword(Map<String, String> map) {
        Users user = usersDao.getUsersByResetToken(map.get("resetToken"));
        user.setResetToken(null);
        user.setPassword(bCryptPasswordEncoder.encode(map.get("password")));
        usersDao.save(user);
    }

    @Override
    @Transactional
    public UserResponse updateUser(UserUpdateRequest updateRequest) throws AppException {
        validateUser(updateRequest);

        Users user = usersDao.findById(updateRequest.getId()).get();
        user = setData(user, updateRequest);

        return usersMapper.usersToUsersResponseWithAbstracts(usersDao.save(user));
    }

    private Users setData(Users users, UserUpdateRequest updateRequest) {
        users.setEmail(updateRequest.getEmail());
        users.setFirstName(updateRequest.getFirstName());
        users.setLastName(updateRequest.getLastName());
        //users.setGender(Gender.valueOf(updateRequest.getGender()));
        users.setDateOfBirth(updateRequest.getDateOfBirth());
        users.setCountry(updateRequest.getCountry());
        users.setTitle(Title.valueOf(updateRequest.getTitle()));
        users.setUniversity(updateRequest.getUniversity());
        users.setFaculty(updateRequest.getFaculty());
        users.setYearOfStudy(YearOfStudy.valueOf(updateRequest.getYearOfStudy()));
        users.setPhoneNumber(updateRequest.getPhoneNumber());
        users.setNeedVisa(updateRequest.getNeedVisa());
        users.setPassportNumber(updateRequest.getPassportNumber());
        return users;
    }

    @Override
    @Transactional
    public void deleteUser(Long id) throws AppException {
        if(!usersDao.existsById(id)){
            throw new AppException(EntityType.USER, ExceptionType.NOT_FOUND);
        }
        //getOne(id);
        usersDao.deleteById(id);
    }

    @Override
    public UserResponseWithAbstracts getOneForAdmin(Long id) throws AppException {
        UserResponseWithAbstracts user = usersMapper.usersToUsersResponseWithAbstracts(usersDao.findById(id).get());

        if (user == null)
            throw new AppException(EntityType.USER, ExceptionType.NOT_FOUND);

        Arrays.stream(user.getAbstractDtos()).forEach(item -> {
            item.setType(AbstractType.valueOf(item.getType()).getType());
            item.setStatus(Status.findStatus(item.getStatus()).name());
        });
        return user;
    }

    @Override
    public UserResponse getOne(Long id, String token) throws AppException {
        long userId = getUserIDFromToken(token);
        Optional<Users> user = usersDao.findById(id);
        if (!user.isPresent())
            throw new AppException(EntityType.USER, ExceptionType.NOT_FOUND);
        if(user.get().getId() != userId){
            throw new AppException(ExceptionType.NO_ACCESS);
        }
        return usersMapper.usersToUsersResponse(user.get());
    }

    public Users getUser(Long id) {
        return usersDao.getOne(id);
    }

    @Override
    public List<UserResponse> getAll(String token) {
        List list = new ArrayList();
        list.add(Role.ROLE_ACTIVE_PARTICIPANT.name());
        list.add(Role.ROLE_PASSIVE_PARTICIPANT.name());
        return usersMapper.usersListToUsersResponseList(usersDao.findAllByRole(list));
    }

    @Override
    public List<UserAdminDtoResponse> getAllAdmins(String token) {
        List<String> list = new ArrayList();
        list.add(Role.ROLE_ADMIN.name());
        return usersMapper.usersAdminListToUsersAdminResponseList(usersDao.findAllByRole(list));
    }

    @Override
    @Transactional
    public UserAdminDtoResponse createAdmin(UserAdminDtoRequest usersDtoRequest) throws AppException {
        usersDtoRequest = validateAdminAuthenticate(usersDtoRequest);

        if (!Validation.emailValidation(usersDtoRequest.getEmail()))
            throw new AppException(ExceptionType.EMAIL_FORMAT);

        Users users = usersDao.save(usersMapper.usersDtoToUsers(usersDtoRequest));
        emailService.sendCreateEmail(users);
        return usersMapper.usersAdminToUsersAdminResponse(users);
    }

    private UserAdminDtoRequest validateAdminAuthenticate(UserAdminDtoRequest userDto) throws AppException {
        userDto.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));

        Set<Authorities> authoritiesDtos = new HashSet<>();
        Authorities authoritiesAdmin = authoritiesService.findByRole(Role.ROLE_ADMIN);
        authoritiesDtos.add(authoritiesAdmin);
        userDto.setAuthoritiesSet(authoritiesService.dtosToEntities(authoritiesDtos));

        return userDto;
    }

    public Boolean checkIfAdmin(String token) throws AccessException {
        String email = getEmailFromToken(token);
        List<String> authorities = authoritiesService.getUserAuthorities(email);
        if(!authorities.contains(Role.ROLE_ADMIN.name())) {
            throw new AccessException(ExceptionType.NO_ACCESS);
        }
        return true;
    }

}
