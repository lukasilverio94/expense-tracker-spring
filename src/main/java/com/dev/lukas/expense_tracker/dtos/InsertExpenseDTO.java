package com.dev.lukas.expense_tracker.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record InsertExpenseDTO(
        @NotBlank(message = "Field required")
        String description,
        @NotNull(message = "Field required")
        BigDecimal value,
        @NotNull(message = "Field required")
        Long categoryId
) {}
