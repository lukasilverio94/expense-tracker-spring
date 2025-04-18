package com.dev.lukas.expense_tracker.controllers.mappers;

import com.dev.lukas.expense_tracker.dtos.CategoryRequestDTO;
import com.dev.lukas.expense_tracker.dtos.CategoryResponseDTO;
import com.dev.lukas.expense_tracker.models.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class CategoryMapper {

    public abstract Category toCategory(CategoryRequestDTO dto);

    public abstract CategoryResponseDTO toDto(Category category);
}
