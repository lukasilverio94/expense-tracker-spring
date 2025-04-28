package com.dev.lukas.expensetracker.exceptions;

public class CategoryExistsException extends RuntimeException {
    public CategoryExistsException(String message) {
        super(message);
    }
}
