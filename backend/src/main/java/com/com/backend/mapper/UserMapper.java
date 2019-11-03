package com.com.backend.mapper;

import com.com.backend.dto.request.UserCreateRequest;
import com.com.backend.dto.request.UserRequest;
import com.com.backend.dto.response.UserResponse;
import com.com.backend.dto.response.UserResponseWithAbstracts;
import com.com.backend.model.Users;
import com.com.backend.model.enums.YearOfStudy;
import com.com.backend.dto.UsersDto;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring", imports = YearOfStudy.class, uses = {AuthoritiesMapper.class})
public interface UserMapper {

    @Mappings({
            @Mapping(target = "id", source = "users.id"),
            @Mapping(target = "email", source = "users.email"),
            @Mapping(target = "password", source = "users.password"),
            @Mapping(target = "registrationDate", source = "users.registrationDate"),
            @Mapping(target = "activatedAccount", source = "users.activatedAccount"),
            @Mapping(target = "firstName", source = "users.firstName"),
            @Mapping(target = "lastName", source = "users.lastName"),
            //@Mapping(target = "gender", source = "users.gender"),
            @Mapping(target = "dateOfBirth", source = "users.dateOfBirth"),
            @Mapping(target = "country", source = "users.country"),
            @Mapping(target = "title", source = "users.title"),
            @Mapping(target = "university", source = "users.university"),
            @Mapping(target = "faculty", source = "users.faculty"),
            @Mapping(target = "yearOfStudy", source = "users.yearOfStudy.year"),
            @Mapping(target = "phoneNumber", source = "users.phoneNumber"),
            @Mapping(target = "needVisa", source = "users.needVisa"),
            @Mapping(target = "passportNumber", source = "users.passportNumber"),
            @Mapping(target = "authoritiesSet", source = "users.authoritiesSet"),
            @Mapping(target = "authorities", ignore = true),
            @Mapping(target = "abstractDtos", ignore = true)
    })
    @Named("toDto")
    UsersDto usersToUsersDto(Users users);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "email", source = "usersDto.email"),
            @Mapping(target = "password", source = "usersDto.password"),
            @Mapping(target = "registrationDate", source = "usersDto.registrationDate"),
            @Mapping(target = "activatedAccount", source = "usersDto.activatedAccount"),
            @Mapping(target = "firstName", source = "usersDto.firstName"),
            @Mapping(target = "lastName", source = "usersDto.lastName"),
            //@Mapping(target = "gender", source = "usersDto.gender"),
            @Mapping(target = "dateOfBirth", source = "usersDto.dateOfBirth"),
            @Mapping(target = "country", source = "usersDto.country"),
            @Mapping(target = "title", source = "usersDto.title"),
            @Mapping(target = "university", source = "usersDto.university"),
            @Mapping(target = "faculty", source = "usersDto.faculty"),
            @Mapping(target = "yearOfStudy", expression = "java(YearOfStudy.valueOf(usersDto.getYearOfStudy()))"),
            @Mapping(target = "phoneNumber", source = "usersDto.phoneNumber"),
            @Mapping(target = "needVisa", source = "usersDto.needVisa"),
            @Mapping(target = "passportNumber", source = "usersDto.passportNumber"),
            @Mapping(target = "authoritiesSet", source = "usersDto.authoritiesSet"),
            @Mapping(target = "abstracts", ignore = true)
    })
    @Named("to")
    Users usersDtoToUsers(UserCreateRequest usersDto);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "email", source = "usersDto.email"),
            @Mapping(target = "password", ignore = true),
            @Mapping(target = "registrationDate", ignore = true),
            @Mapping(target = "activatedAccount", ignore = true),
            @Mapping(target = "firstName", source = "usersDto.firstName"),
            @Mapping(target = "lastName", source = "usersDto.lastName"),
            //@Mapping(target = "gender", source = "usersDto.gender"),
            @Mapping(target = "dateOfBirth", source = "usersDto.dateOfBirth"),
            @Mapping(target = "country", source = "usersDto.country"),
            @Mapping(target = "title", source = "usersDto.title"),
            @Mapping(target = "university", source = "usersDto.university"),
            @Mapping(target = "faculty", source = "usersDto.faculty"),
            @Mapping(target = "yearOfStudy", expression = "java(YearOfStudy.valueOf(usersDto.getYearOfStudy()))"),
            @Mapping(target = "phoneNumber", source = "usersDto.phoneNumber"),
            @Mapping(target = "needVisa", source = "usersDto.needVisa"),
            @Mapping(target = "authoritiesSet", ignore = true),
            @Mapping(target = "abstracts", ignore = true)
    })
    @Named("to")
    Users usersRequestToUsers(UserRequest usersDto);

    @Mappings({
            @Mapping(target = "id", source = "users.id"),
            @Mapping(target = "email", source = "users.email"),
            @Mapping(target = "firstName", source = "users.firstName"),
            @Mapping(target = "lastName", source = "users.lastName"),
            //@Mapping(target = "gender", source = "users.gender"),
            @Mapping(target = "dateOfBirth", source = "users.dateOfBirth"),
            @Mapping(target = "country", source = "users.country"),
            @Mapping(target = "title", source = "users.title"),
            @Mapping(target = "university", source = "users.university"),
            @Mapping(target = "faculty", source = "users.faculty"),
            @Mapping(target = "yearOfStudy", source = "users.yearOfStudy.year"),
            @Mapping(target = "phoneNumber", source = "users.phoneNumber"),
            @Mapping(target = "needVisa", source = "users.needVisa"),
            @Mapping(target = "passportNumber", source = "users.passportNumber"),
            @Mapping(target = "authoritiesSet", source = "users.authoritiesSet")
    })
    @Named("resTo")
    UserResponse usersToUsersResponse(Users users);
    @IterableMapping(qualifiedByName = "resTo")
    List<UserResponse> usersListToUsersResponseList(List<Users> users);

    @Mappings({
            @Mapping(target = "id", source = "users.id"),
            @Mapping(target = "email", source = "users.email"),
            @Mapping(target = "firstName", source = "users.firstName"),
            @Mapping(target = "lastName", source = "users.lastName"),
            //@Mapping(target = "gender", source = "users.gender"),
            @Mapping(target = "dateOfBirth", source = "users.dateOfBirth"),
            @Mapping(target = "country", source = "users.country"),
            @Mapping(target = "title", source = "users.title"),
            @Mapping(target = "university", source = "users.university"),
            @Mapping(target = "faculty", source = "users.faculty"),
            @Mapping(target = "yearOfStudy", source = "users.yearOfStudy.year"),
            @Mapping(target = "phoneNumber", source = "users.phoneNumber"),
            @Mapping(target = "needVisa", source = "users.needVisa"),
            @Mapping(target = "passportNumber", source = "users.passportNumber"),
            @Mapping(target = "authoritiesSet", source = "users.authoritiesSet"),
            @Mapping(target = "abstractDtos", source = "users.abstracts")
    })
    UserResponseWithAbstracts usersToUsersResponseWithAbstracts(Users users);

}
