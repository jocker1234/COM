package com.com.backend.service;

import com.com.backend.dto.CategoryDto;
import com.com.backend.exception.NotFoundException;

import java.util.List;

public interface CategoryService {

    List<CategoryDto> getAll();
    List<CategoryDto> create(CategoryDto categoryDto);
    List<CategoryDto> delete(Long id) throws NotFoundException;
    List<CategoryDto> update(Long id, CategoryDto categoryDto) throws NotFoundException;
}
