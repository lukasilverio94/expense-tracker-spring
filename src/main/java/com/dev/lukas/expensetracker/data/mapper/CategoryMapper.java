package com.dev.lukas.expensetracker.data.mapper;

import com.dev.lukas.expensetracker.data.dtos.CategoryDto;
import com.dev.lukas.expensetracker.data.entity.Category;
import com.dev.lukas.expensetracker.data.model.CategoryRequestModel;
import com.dev.lukas.expensetracker.data.model.CategoryResponseModel;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public Category dtoToEntity(CategoryDto dto) {
        return new Category(dto.id(), dto.name());
    }

    public CategoryDto entityToDto(Category entity) {
        return new CategoryDto(
                entity.getId(),
                entity.getName()
        );
    }

    public CategoryDto modelToDto(CategoryRequestModel dto) {
        return new CategoryDto(
                dto.id(),
                dto.name()
        );
    }

    public CategoryResponseModel dtoToModel(CategoryDto dto) {
        return new CategoryResponseModel(dto.name());
    }

}
