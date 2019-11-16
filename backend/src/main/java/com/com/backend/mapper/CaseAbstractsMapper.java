package com.com.backend.mapper;

import com.com.backend.dto.response.CaseAbstractsDtoResponse;
import com.com.backend.model.enums.AbstractType;
import com.com.backend.model.enums.Status;
import com.com.backend.util.Util;
import com.com.backend.model.CaseAbstracts;
import com.com.backend.dto.request.CaseAbstractsDtoRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", imports = {Util.class, AbstractType.class, Status.class})
public interface CaseAbstractsMapper extends AbstractsMapper<CaseAbstractsDtoRequest, CaseAbstractsDtoResponse, CaseAbstracts> {

    @Mappings({
            @Mapping(source = "s.id", target = "id"),
            @Mapping(source = "s.title", target = "title"),
            @Mapping(source = "s.category", target = "category"),
            @Mapping(source = "s.authors", target = "authors"),
            @Mapping(source = "s.affiliation", target = "affiliation"),
            @Mapping(source = "s.tutors", target = "tutors"),
            @Mapping(expression = "java(Status.findStatus(s.getStatus()).name())", target = "status"),
            @Mapping(expression = "java(s.getType().getDesc())", target = "type"),
            @Mapping(source = "s.background", target = "background"),
            @Mapping(source = "s.caseReport", target = "caseReport"),
            @Mapping(source = "s.conclusions", target = "conclusions")
    })
    CaseAbstractsDtoResponse modelToDtoRes(CaseAbstracts s);

    @Mappings({
            @Mapping(source = "s.id", target = "id"),
            @Mapping(source = "s.title", target = "title"),
            @Mapping(source = "s.category", target = "category"),
            @Mapping(source = "s.authors", target = "authors"),
            @Mapping(source = "s.tutors", target = "tutors"),
            @Mapping(source = "s.affiliation", target = "affiliation"),
            @Mapping(source = "s.status", target = "status"),
            @Mapping(expression = "java(AbstractType.valueOf(s.getType()))", target = "type"),
            @Mapping(source = "s.background", target = "background"),
            @Mapping(source = "s.caseReport", target = "caseReport"),
            @Mapping(source = "s.conclusions", target = "conclusions"),
            @Mapping(target = "users", ignore = true)
    })
    CaseAbstracts dtoResToModel(CaseAbstractsDtoResponse s);

    @Mappings({
            @Mapping(source = "s.id", target = "id"),
            @Mapping(source = "s.title", target = "title"),
            @Mapping(source = "s.category.id", target = "categoryId"),
            @Mapping(source = "s.authors", target = "authors"),
            @Mapping(source = "s.tutors", target = "tutors"),
            @Mapping(source = "s.affiliation", target = "affiliation"),
            @Mapping(source = "s.status", target = "status"),
            @Mapping(source = "s.type", target = "type"),
            @Mapping(source = "s.background", target = "background"),
            @Mapping(source = "s.caseReport", target = "caseReport"),
            @Mapping(source = "s.conclusions", target = "conclusions")
    })
    CaseAbstractsDtoRequest modelToDtoReq(CaseAbstracts s);

    @Mappings({
            @Mapping(source = "s.id", target = "id"),
            @Mapping(source = "s.title", target = "title"),
            @Mapping(target = "category", ignore = true),
            @Mapping(source = "s.authors", target = "authors"),
            @Mapping(source = "s.tutors", target = "tutors"),
            @Mapping(source = "s.affiliation", target = "affiliation"),
            @Mapping(source = "s.status", target = "status"),
            @Mapping(expression = "java(AbstractType.valueOf(s.getType()))", target = "type"),
            @Mapping(source = "s.background", target = "background"),
            @Mapping(source = "s.caseReport", target = "caseReport"),
            @Mapping(source = "s.conclusions", target = "conclusions"),
            @Mapping(target = "users", ignore = true)
    })
    CaseAbstracts dtoReqToModel(CaseAbstractsDtoRequest s);

}
