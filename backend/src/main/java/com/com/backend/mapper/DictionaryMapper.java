package com.com.backend.mapper;

import com.com.backend.dto.request.DictionaryDtoRequest;
import com.com.backend.dto.response.DictionaryDtoResponse;
import com.com.backend.model.Dictionary;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DictionaryMapper {

    @Mappings({
            @Mapping(target = "id", source = "directoryDtoRequest.id"),
            @Mapping(target = "key", source = "directoryDtoRequest.key"),
            @Mapping(target = "value", source = "directoryDtoRequest.value"),
            @Mapping(target = "addDate", ignore = true),
            @Mapping(target = "editDate", ignore = true),
            @Mapping(target = "image", ignore = true)
    })
    @Named("to")
    Dictionary dtoReqToModel(DictionaryDtoRequest directoryDtoRequest);

    @Mappings({
            @Mapping(target = "id", source = "directory.id"),
            @Mapping(target = "addDate", source = "directory.addDate"),
            @Mapping(target = "editDate", source = "directory.editDate"),
            @Mapping(target = "key", source = "directory.key"),
            @Mapping(target = "value", source = "directory.value"),
            @Mapping(target = "image", ignore = true)
    })
    @Named("toDto")
    DictionaryDtoResponse modelToDtoRes(Dictionary directory);

    @IterableMapping(qualifiedByName = "toDto")
    List<DictionaryDtoResponse> listToListDtoRes(List<Dictionary> directoryList);

}
