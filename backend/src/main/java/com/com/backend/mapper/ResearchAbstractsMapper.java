package com.com.backend.mapper;

import com.com.backend.Util;
import com.com.backend.domain.ResearchAbstracts;
import com.com.backend.dto.ResearchAbstractsDto;
import com.com.backend.service.CategoryService;
import com.com.backend.service.ResearchAbstractsService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", imports = Util.class, uses = {CategoryService.class})
public interface ResearchAbstractsMapper extends AbstractsMapper<ResearchAbstractsDto, ResearchAbstracts> {

    @Mappings({
            @Mapping(source = "s.id", target = "id"),
            @Mapping(source = "s.title", target = "title"),
            @Mapping(target = "authors", expression = "java(Util.splitWithComma(s.getAuthors()))"),
            @Mapping(source = "s.tutors", target = "tutors"),
            @Mapping(source = "s.status", target = "status"),
            @Mapping(source = "s.type", target = "type"),
            @Mapping(source = "s.introdution", target = "introdution"),
            @Mapping(source = "s.aimOfTheStudy", target = "aimOfTheStudy"),
            @Mapping(source = "s.materialAndMethods", target = "materialAndMethods"),
            @Mapping(source = "s.results", target = "results"),
            @Mapping(source = "s.conclusions", target = "conclusions")
    })
    ResearchAbstractsDto dtoToModel(ResearchAbstracts s);

    @Mappings({
            @Mapping(source = "s.id", target = "id"),
            @Mapping(source = "s.title", target = "title"),
            @Mapping(target = "authors", expression = "java(Util.joinWithComma(s.getAuthors()))"),
            @Mapping(source = "s.tutors", target = "tutors"),
            @Mapping(source = "s.status", target = "status"),
            @Mapping(source = "s.type", target = "type"),
            @Mapping(source = "s.introdution", target = "introdution"),
            @Mapping(source = "s.aimOfTheStudy", target = "aimOfTheStudy"),
            @Mapping(source = "s.materialAndMethods", target = "materialAndMethods"),
            @Mapping(source = "s.results", target = "results"),
            @Mapping(source = "s.conclusions", target = "conclusions")
    })
    ResearchAbstracts modelToDto(ResearchAbstractsDto s);

}
