package com.dev.lukas.expensetracker.data.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ExpenseRequestModel(
                @NotBlank(message = "Field required") String description,
                @NotNull(message = "Field required") BigDecimal value,
                @NotNull(message = "Field required") Long categoryId) {
}
