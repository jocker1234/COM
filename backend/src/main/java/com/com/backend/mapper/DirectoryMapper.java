package com.com.backend.mapper;

import com.com.backend.dto.request.DirectoryDtoRequest;
import com.com.backend.dto.response.DirectoryDtoResponse;
import com.com.backend.model.Directory;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DirectoryMapper {

    @Mappings({
            @Mapping(target = "id", source = "directoryDtoRequest.id"),
            @Mapping(target = "key", source = "directoryDtoRequest.key"),
            @Mapping(target = "value", source = "directoryDtoRequest.value")
    })
    @Named("to")
    Directory dtoReqToModel(DirectoryDtoRequest directoryDtoRequest);

    @Mappings({
            @Mapping(target = "id", source = "directory.id"),
            @Mapping(target = "addDate", source = "directory.addDate"),
            @Mapping(target = "editDate", source = "directory.editDate"),
            @Mapping(target = "key", source = "directory.key"),
            @Mapping(target = "value", source = "directory.value")
    })
    @Named("toDto")
    DirectoryDtoResponse modelToDtoRes(Directory directory);

    @IterableMapping(qualifiedByName = "toDto")
    List<DirectoryDtoResponse> listToListDtoRes(List<Directory> directoryList);

}
