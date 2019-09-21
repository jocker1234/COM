package com.com.backend.mapper;

import com.com.backend.model.Category;
import com.com.backend.dto.CategoryDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    List<CategoryDto> dataToEntities(List<Category> category);
    CategoryDto dataToEntity(Category category);
    Category entityToData(CategoryDto categoryDto);

}
