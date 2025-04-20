package com.dev.lukas.expensetracker.controllers.mappers;

import com.dev.lukas.expensetracker.domain.dtos.CategoryDTO;
import com.dev.lukas.expensetracker.domain.models.Category;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class CategoryDTOMapper implements Function<Category, CategoryDTO> {

    @Override
    public CategoryDTO apply(Category category) {
        return new CategoryDTO(
                category.getId(),
                category.getName()
        );
    }
}
