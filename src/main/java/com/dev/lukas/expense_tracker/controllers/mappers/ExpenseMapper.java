package com.dev.lukas.expense_tracker.controllers.mappers;

import com.dev.lukas.expense_tracker.dtos.ExpenseRequestDTO;
import com.dev.lukas.expense_tracker.dtos.ExpenseResponseDTO;
import com.dev.lukas.expense_tracker.models.Expense;
import com.dev.lukas.expense_tracker.repositories.CategoryRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class ExpenseMapper {

    @Autowired
    protected CategoryRepository categoryRepository;

    @Mapping(target = "category",
            expression = "java( categoryRepository.findById(dto.categoryId()).orElse(null) )"
    )
    public abstract Expense toExpense(ExpenseRequestDTO dto);

    @Mapping(target = "category", source = "category")
    public abstract ExpenseResponseDTO toGetExpenseDTO(Expense expense);

}
