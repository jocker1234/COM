package com.com.backend.mapper;

import com.com.backend.model.Authorities;
import com.com.backend.model.enums.Role;
import com.com.backend.dto.AuthoritiesDto;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import java.util.Set;

@Mapper(componentModel = "spring", imports = Role.class)
public interface AuthoritiesMapper {

    @Mappings({
            @Mapping(target = "id", source = "authorities.id"),
            @Mapping(target = "roleName", source = "authorities.roleName.desc")
    })
    @Named("toDto")
    AuthoritiesDto authoritiesToAuthoritiesDto(Authorities authorities);

    @IterableMapping(qualifiedByName = "toDto")
    Set<AuthoritiesDto> authoritiessToAuthoritiesDtos(Set<Authorities> users);

    @Mappings({
            @Mapping(target = "id", source = "authoritiesDto.id"),
            @Mapping(target = "roleName", expression = "java(Role.findRole(authoritiesDto.getRoleName()))")
    })
    @Named("toEntity")
    Authorities authoritiesDtoToAuthorities(AuthoritiesDto authoritiesDto);

    @IterableMapping(qualifiedByName = "toEntity")
    Set<Authorities> authoritiesDtosToAuthoritiess(Set<AuthoritiesDto> users);

}
