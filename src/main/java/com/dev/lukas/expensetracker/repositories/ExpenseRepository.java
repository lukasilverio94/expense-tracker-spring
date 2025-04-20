package com.dev.lukas.expensetracker.repositories;

import com.dev.lukas.expensetracker.data.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findByDescription(String description);
}
