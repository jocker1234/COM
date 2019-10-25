package com.com.backend.controller;

import com.com.backend.dto.CategoryDto;
import com.com.backend.exception.NotFoundException;
import com.com.backend.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(value = {"http://localhost:4200", "http://54.37.234.192:4200"})
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACTIVE_PARTICIPANT', 'ROLE_PASSIVE_PARTICIPANT')")
    public ResponseEntity<List<CategoryDto>> getAll() {
        return ResponseEntity.ok(categoryService.getAll());
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<CategoryDto>> create(@Valid @RequestBody CategoryDto categoryRequest) {
        List<CategoryDto> categoryDtos = categoryService.create(categoryRequest);
        return ResponseEntity.ok(categoryDtos);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<CategoryDto>> delete(@Valid @PathVariable Long id) throws NotFoundException {
        List<CategoryDto> categoryDtos = categoryService.delete(id);
        return ResponseEntity.ok(categoryDtos);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<CategoryDto>> update(@Valid @PathVariable Long id, @RequestBody CategoryDto categoryDto) throws NotFoundException {
        List<CategoryDto> categoryDtos = categoryService.update(id, categoryDto);
        return ResponseEntity.ok(categoryDtos);
    }

}
