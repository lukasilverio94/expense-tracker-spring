package com.dev.lukas.expense_tracker.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.lukas.expense_tracker.entities.Expense;
import com.dev.lukas.expense_tracker.services.ExpenseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(value = "/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @GetMapping
    public ResponseEntity<List<Expense>> listAll() {
        List<Expense> expenses = expenseService.findAll();
        return ResponseEntity.ok().body(expenses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Expense> findById(@PathVariable Long id){
        return expenseService.findById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{categoryId}")
    public ResponseEntity<Expense> insert(@PathVariable Long categoryId, @RequestBody Expense expense) {
        Expense newExpense = expenseService.insertWithCategory(categoryId, expense);
        return ResponseEntity.ok(newExpense);
    }

}
