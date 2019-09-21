package com.com.backend.mapper;

import com.com.backend.util.Util;
import com.com.backend.model.CaseAbstracts;
import com.com.backend.dto.CaseAbstractsDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", imports = Util.class)
public interface CaseAbstractsMapper extends AbstractsMapper<CaseAbstractsDto, CaseAbstracts> {

    @Mappings({
            @Mapping(source = "s.id", target = "id"),
            @Mapping(source = "s.title", target = "title"),
            @Mapping(target = "authors", expression = "java(Util.splitWithComma(s.getAuthors()))"),
            @Mapping(source = "s.tutors", target = "tutors"),
            @Mapping(source = "s.status", target = "status"),
            @Mapping(source = "s.type", target = "type"),
            @Mapping(source = "s.background", target = "background"),
            @Mapping(source = "s.caseReport", target = "caseReport"),
            @Mapping(source = "s.conclusions", target = "conclusions")
    })
    CaseAbstractsDto dtoToModel(CaseAbstracts s);

    @Mappings({
            @Mapping(source = "s.id", target = "id"),
            @Mapping(source = "s.title", target = "title"),
            @Mapping(target = "authors", expression = "java(Util.joinWithComma(s.getAuthors()))"),
            @Mapping(source = "s.tutors", target = "tutors"),
            @Mapping(source = "s.status", target = "status"),
            @Mapping(source = "s.type", target = "type"),
            @Mapping(source = "s.background", target = "background"),
            @Mapping(source = "s.caseReport", target = "caseReport"),
            @Mapping(source = "s.conclusions", target = "conclusions")
    })
    CaseAbstracts modelToDto(CaseAbstractsDto s);

}
