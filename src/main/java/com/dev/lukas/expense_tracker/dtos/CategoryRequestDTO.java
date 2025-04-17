package com.dev.lukas.expense_tracker.dtos;

import jakarta.validation.constraints.NotBlank;

public record CategoryRequestDTO(
        @NotBlank(message = "Field required")
        Long id,
        @NotBlank(message = "Field required")
        String name) {
}

