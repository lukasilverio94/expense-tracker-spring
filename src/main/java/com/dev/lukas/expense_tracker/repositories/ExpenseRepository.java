package com.dev.lukas.expense_tracker.repositories;

import com.dev.lukas.expense_tracker.models.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findByDescription(String description);
}
