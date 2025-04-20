package com.dev.lukas.expensetracker.data.mapper;

import com.dev.lukas.expensetracker.data.dtos.ExpenseDto;
import com.dev.lukas.expensetracker.data.entity.Expense;
import com.dev.lukas.expensetracker.data.model.ExpenseRequestModel;
import com.dev.lukas.expensetracker.data.model.ExpenseResponseModel;
import org.springframework.stereotype.Component;

@Component
public class ExpenseMapper {
    public Expense dtoToEntity(ExpenseDto dto) {
        return null;
    }

    public ExpenseDto entityToDto(Expense entity) {
        return null;
    }

    public ExpenseDto modelToDto(ExpenseRequestModel model) {
        return null;
    }

    public ExpenseResponseModel dtoToModel(ExpenseDto dto) {
        return null;
    }
}
