package com.example.blogapp.service.impl;

import com.example.blogapp.payload.CategoryDto;
import com.example.blogapp.repository.CategoryRepository;
import com.example.blogapp.service.CategoryService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryDto addCategory(CategoryDto categoryDto) {
        return null;
    }

    @Override
    public CategoryDto getCategory(Long categoryId) {
        return null;
    }

    @Override
    public List<CategoryDto> getCategories() {
        return List.of();
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId) {
        return null;
    }

    @Override
    public void deleteCategory(Long categoryID) {

    }
}
