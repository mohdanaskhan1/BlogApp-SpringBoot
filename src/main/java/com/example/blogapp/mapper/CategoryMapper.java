package com.example.blogapp.mapper;


import com.example.blogapp.entity.Category;
import com.example.blogapp.payload.CategoryDto;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    public Category toEntity(CategoryDto categoryDto) {
        if (categoryDto == null) return null;
        return Category.builder()
                .name(categoryDto.getName())
                .description(categoryDto.getDescription())
                .build();
    }

    public CategoryDto toDTO(Category category) {
        if (category == null) return null;
        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .build();
    }

}
