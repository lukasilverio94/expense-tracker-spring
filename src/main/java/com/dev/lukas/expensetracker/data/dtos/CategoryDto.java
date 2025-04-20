package com.dev.lukas.expensetracker.data.dtos;

import com.dev.lukas.expensetracker.data.entity.Expense;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {
    private Long id;
    private String name;
    private List<Expense> expenses;
}
