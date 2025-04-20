package com.dev.lukas.expensetracker.data.dtos;

import com.dev.lukas.expensetracker.data.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseDto {
    private Long id;
    private String description;
    private BigDecimal value;
    private Category category;
}
