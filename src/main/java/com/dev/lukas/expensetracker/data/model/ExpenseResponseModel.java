package com.dev.lukas.expensetracker.data.model;

import com.dev.lukas.expensetracker.data.entity.Category;

import java.math.BigDecimal;

public record ExpenseResponseModel(
                Long id,
                String description,
                BigDecimal value,
                Category category) {
}
