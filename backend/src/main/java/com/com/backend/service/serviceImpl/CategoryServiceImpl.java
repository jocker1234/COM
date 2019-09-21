package com.com.backend.service.serviceImpl;

import com.com.backend.dao.CategoryDao;
import com.com.backend.model.Category;
import com.com.backend.dto.CategoryDto;
import com.com.backend.mapper.CategoryMapper;
import com.com.backend.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryDao categoryDao;
    private final CategoryMapper categoryMapper;

    public CategoryServiceImpl(CategoryDao categoryDao, CategoryMapper categoryMapper) {
        this.categoryDao = categoryDao;
        this.categoryMapper = categoryMapper;
    }

    private Category getOne(Long id){
        return categoryDao.getOne(id);
    }

    public List<CategoryDto> getAll(){
        return categoryMapper.dataToEntities(categoryDao.findAll());
    }

    @Override
    public List<CategoryDto> create(CategoryDto categoryDto) {
        Category category = categoryMapper.entityToData(categoryDto);
        categoryDao.save(category);
        return getAll();
    }

    @Override
    public List<CategoryDto> delete(Long id) {
        categoryDao.delete(getOne(id));
        return getAll();
    }

    @Override
    public List<CategoryDto> update(Long id, CategoryDto categoryDto) {
        Category category = categoryMapper.entityToData(categoryDto);
        categoryDao.save(category);
        return this.getAll();
    }

}
