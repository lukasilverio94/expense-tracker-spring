package com.dev.lukas.expense_tracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.lukas.expense_tracker.entities.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    
}
