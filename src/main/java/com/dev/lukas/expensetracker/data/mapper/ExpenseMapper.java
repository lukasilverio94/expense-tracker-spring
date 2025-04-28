package com.dev.lukas.expensetracker.data.mapper;

import com.dev.lukas.expensetracker.data.dtos.ExpenseDto;
import com.dev.lukas.expensetracker.data.entity.Category;
import com.dev.lukas.expensetracker.data.entity.Expense;
import com.dev.lukas.expensetracker.data.model.ExpenseRequestModel;
import com.dev.lukas.expensetracker.data.model.ExpenseResponseModel;
import org.springframework.stereotype.Component;

@Component
public class ExpenseMapper {
    public Expense dtoToEntity(ExpenseDto dto) {
        Expense entity = new Expense();
        entity.setId(dto.id());
        entity.setDescription(dto.description());
        entity.setValue(dto.value());
        entity.setCategory(dto.categoryId());
        return entity;
    }

    public ExpenseDto entityToDto(Expense entity) {
        return new ExpenseDto(
                entity.getId(),
                entity.getDescription(),
                entity.getValue(),
                entity.getCategory().getId()pq
        );
    }

    public ExpenseDto modelToDto(ExpenseRequestModel model) {
        return new ExpenseDto(
                null,
                model.description(),
                model.value(),
                model.categoryId()
        );
    }

    public ExpenseResponseModel dtoToModel(ExpenseDto dto) {
        return new ExpenseResponseModel(
                dto.id(),
                dto.description(),
                dto.value(),
        );

    }
}