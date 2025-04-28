package com.dev.lukas.expensetracker.data.dtos;

import java.math.BigDecimal;

public record ExpenseDto(
        Long id,
        String description,
        BigDecimal value,
        Long categoryId
) {}
