package com.dev.lukas.expensetracker.repositories;

import com.dev.lukas.expensetracker.domain.models.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;

public interface ExpenseRepository extends JpaRepository<Expense, Long>, JpaSpecificationExecutor<Expense> {

    @Query("select sum(e.value) from Expense e")
    BigDecimal findTotalExpenses();
}
