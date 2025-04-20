package com.dev.lukas.expensetracker.domain.dtos;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ExpenseDTO(
        @NotBlank(message = "Description required") String description,
        @NotNull(message = "Value required") BigDecimal value,
        @NotNull(message = "Category Id required") Long categoryId) {
}
