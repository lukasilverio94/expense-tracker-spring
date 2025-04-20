package com.dev.lukas.expensetracker.data.model;

import jakarta.validation.constraints.NotBlank;

public record CategoryRequestModel(
        @NotBlank(message = "Field required")
        Long id,
        @NotBlank(message = "Field required")
        String name) {
}

