package com.dev.lukas.expensetracker.repositories;

import com.dev.lukas.expensetracker.domain.models.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long>, JpaSpecificationExecutor<Expense> {

    List<Expense> findByDescription(String description);

    List<Expense> findAllByOrderByCreatedAtDesc();

    @Query("select sum(e.value) from Expense e")
    BigDecimal findTotalExpenses();
}
