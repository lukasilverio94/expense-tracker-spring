package com.dev.lukas.expense_tracker.dtos;

import com.dev.lukas.expense_tracker.models.Category;

import java.math.BigDecimal;

public record ExpenseResponseDTO(
                Long id,
                String description,
                BigDecimal value,
                Category category) {
}
