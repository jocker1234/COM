package com.com.backend.mapper;

import com.com.backend.dto.request.ResearchAbstractsDtoRequest;
import com.com.backend.dto.response.ResearchAbstractsDtoResponse;
import com.com.backend.util.Util;
import com.com.backend.model.ResearchAbstracts;
import com.com.backend.service.CategoryService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", imports = Util.class, uses = {CategoryService.class})
public interface ResearchAbstractsMapper extends AbstractsMapper<ResearchAbstractsDtoRequest,
                                                                    ResearchAbstractsDtoResponse, ResearchAbstracts> {

    @Mappings({
            @Mapping(source = "s.id", target = "id"),
            @Mapping(source = "s.title", target = "title"),
            @Mapping(source = "s.category", target = "category"),
            @Mapping(source = "s.authors", target = "authors"),
            @Mapping(source = "s.tutors", target = "tutors"),
            @Mapping(source = "s.status", target = "status"),
            @Mapping(source = "s.type", target = "type"),
            @Mapping(source = "s.introduction", target = "introduction"),
            @Mapping(source = "s.aimOfTheStudy", target = "aimOfTheStudy"),
            @Mapping(source = "s.materialAndMethods", target = "materialAndMethods"),
            @Mapping(source = "s.results", target = "results"),
            @Mapping(source = "s.conclusions", target = "conclusions")
    })
    ResearchAbstractsDtoResponse modelToDtoRes(ResearchAbstracts s);

    @Mappings({
            @Mapping(source = "s.id", target = "id"),
            @Mapping(source = "s.title", target = "title"),
            @Mapping(source = "s.category", target = "category"),
            @Mapping(source = "s.authors", target = "authors"),
            @Mapping(source = "s.tutors", target = "tutors"),
            @Mapping(source = "s.status", target = "status"),
            @Mapping(source = "s.type", target = "type"),
            @Mapping(source = "s.introduction", target = "introduction"),
            @Mapping(source = "s.aimOfTheStudy", target = "aimOfTheStudy"),
            @Mapping(source = "s.materialAndMethods", target = "materialAndMethods"),
            @Mapping(source = "s.results", target = "results"),
            @Mapping(source = "s.conclusions", target = "conclusions"),
            @Mapping(target = "users", ignore = true)
    })
    ResearchAbstracts dtoResToModel(ResearchAbstractsDtoResponse s);

    @Mappings({
            @Mapping(source = "s.id", target = "id"),
            @Mapping(source = "s.title", target = "title"),
            @Mapping(source = "s.category.id", target = "categoryId"),
            @Mapping(source = "s.authors", target = "authors"),
            @Mapping(source = "s.tutors", target = "tutors"),
            @Mapping(source = "s.status", target = "status"),
            @Mapping(source = "s.type", target = "type"),
            @Mapping(source = "s.introduction", target = "introduction"),
            @Mapping(source = "s.aimOfTheStudy", target = "aimOfTheStudy"),
            @Mapping(source = "s.materialAndMethods", target = "materialAndMethods"),
            @Mapping(source = "s.results", target = "results"),
            @Mapping(source = "s.conclusions", target = "conclusions")
    })
    ResearchAbstractsDtoRequest modelToDtoReq(ResearchAbstracts s);

    @Mappings({
            @Mapping(source = "s.id", target = "id"),
            @Mapping(source = "s.title", target = "title"),
            @Mapping(target = "category", ignore = true),
            @Mapping(source = "s.authors", target = "authors"),
            @Mapping(source = "s.tutors", target = "tutors"),
            @Mapping(source = "s.status", target = "status"),
            @Mapping(source = "s.type", target = "type"),
            @Mapping(source = "s.introduction", target = "introduction"),
            @Mapping(source = "s.aimOfTheStudy", target = "aimOfTheStudy"),
            @Mapping(source = "s.materialAndMethods", target = "materialAndMethods"),
            @Mapping(source = "s.results", target = "results"),
            @Mapping(source = "s.conclusions", target = "conclusions"),
            @Mapping(target = "users", ignore = true)
    })
    ResearchAbstracts dtoReqToModel(ResearchAbstractsDtoRequest s);

}
