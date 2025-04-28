package com.dev.lukas.expensetracker.domain.dtos;

import jakarta.validation.constraints.NotNull;

public record CategoryDTO(
        Long id,
        @NotNull(message = "Category name is required")
        String name) {
}
