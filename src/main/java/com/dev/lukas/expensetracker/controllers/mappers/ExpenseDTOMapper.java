package com.dev.lukas.expensetracker.controllers.mappers;

import com.dev.lukas.expensetracker.domain.dtos.CategoryDTO;
import com.dev.lukas.expensetracker.domain.dtos.ExpenseDTO;
import com.dev.lukas.expensetracker.domain.dtos.ExpenseResponseDTO;
import com.dev.lukas.expensetracker.domain.models.Category;
import com.dev.lukas.expensetracker.domain.models.Expense;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.function.Function;

@Service
public class ExpenseDTOMapper implements Function<Expense, ExpenseDTO> {

    // DateTime formatter
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    @Override
    public ExpenseDTO apply(Expense expense) {
        return new ExpenseDTO(
                expense.getDescription(),
                expense.getValue(),
                expense.getCategory().getId(),
                expense.getCreatedAt().format(formatter),
                expense.getUpdatedAt().format(formatter)
        );
    }

    public Expense toEntity(ExpenseDTO dto, Category category) {
        Expense expense = new Expense();
        expense.setDescription(dto.description());
        expense.setValue(dto.value());
        expense.setCategory(category);
        return expense;
    }

    public ExpenseResponseDTO toGetExpenseDTO(Expense expense){
        CategoryDTO categoryDTO = new CategoryDTO(
              expense.getCategory().getId(),
                expense.getCategory().getName()
        );

        return new ExpenseResponseDTO(
                expense.getId(),
                expense.getDescription(),
                expense.getValue(),
                categoryDTO,
                expense.getCreatedAt().toString(),
                expense.getUpdatedAt().toString()
        );
    }

}
