package com.com.backend.mapper;

import com.com.backend.domain.Users;
import com.com.backend.domain.enums.YearOfStudy;
import com.com.backend.dto.UsersDto;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring", imports = YearOfStudy.class, uses = AuthoritiesMapper.class)
public interface UserMapper {

    @Mappings({
            @Mapping(target = "id", source = "users.id"),
            @Mapping(target = "email", source = "users.email"),
            @Mapping(target = "password", source = "users.password"),
            @Mapping(target = "registrationDate", source = "users.registrationDate"),
            @Mapping(target = "activatedAccount", source = "users.activatedAccount"),
            @Mapping(target = "firstName", source = "users.firstName"),
            @Mapping(target = "lastName", source = "users.lastName"),
            @Mapping(target = "gender", source = "users.gender"),
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
    @Named("toDto")
    UsersDto usersToUsersDto(Users users);

    @Mappings({
            @Mapping(target = "id", source = "usersDto.id"),
            @Mapping(target = "email", source = "usersDto.email"),
            @Mapping(target = "password", source = "usersDto.password"),
            @Mapping(target = "registrationDate", source = "usersDto.registrationDate"),
            @Mapping(target = "activatedAccount", source = "usersDto.activatedAccount"),
            @Mapping(target = "firstName", source = "usersDto.firstName"),
            @Mapping(target = "lastName", source = "usersDto.lastName"),
            @Mapping(target = "gender", source = "usersDto.gender"),
            @Mapping(target = "dateOfBirth", source = "usersDto.dateOfBirth"),
            @Mapping(target = "country", source = "usersDto.country"),
            @Mapping(target = "title", source = "usersDto.title"),
            @Mapping(target = "university", source = "usersDto.university"),
            @Mapping(target = "faculty", source = "usersDto.faculty"),
            @Mapping(target = "yearOfStudy", expression = "java(YearOfStudy.valueOf(usersDto.getYearOfStudy()))"),
            @Mapping(target = "phoneNumber", source = "usersDto.phoneNumber"),
            @Mapping(target = "needVisa", source = "usersDto.needVisa"),
            @Mapping(target = "passportNumber", source = "usersDto.passportNumber"),
            @Mapping(target = "authoritiesSet", source = "usersDto.authoritiesSet")
    })
    @Named("to")
    Users usersDtoToUsers(UsersDto usersDto);

    @IterableMapping(qualifiedByName = "to")
    List<Users> userssToUsersDtos(List<UsersDto> usersDtos);

    @IterableMapping(qualifiedByName = "toDto")
    List<UsersDto> usersDtosToUserss(List<Users> users);

}
