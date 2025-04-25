package com.dev.lukas.expensetracker.domain.dtos;

import java.math.BigDecimal;

public record ExpenseResponseDTO(
        Long id,
        String description,
        BigDecimal value,
        CategoryDTO category,
        String createdAt,
        String updatedAt
) {
}
