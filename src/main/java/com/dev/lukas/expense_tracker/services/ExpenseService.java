package com.dev.lukas.expense_tracker.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.lukas.expense_tracker.entities.Category;
import com.dev.lukas.expense_tracker.entities.Expense;
import com.dev.lukas.expense_tracker.repositories.CategoryRepository;
import com.dev.lukas.expense_tracker.repositories.ExpenseRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired 
    private CategoryRepository categoryRepository;

    public List<Expense> findAll() {
        return expenseRepository.findAll();
    }

    public Optional<Expense> findById(Long id) {
        return expenseRepository.findById(id);
    }

    public Expense insert(Expense expense) {
        return expenseRepository.save(expense);
    }

    public Expense insertWithCategory(Long categoryId, Expense expense){
        Category category = categoryRepository.findById(categoryId)
            .orElseThrow(() -> new EntityNotFoundException("Category not found with ID: " + categoryId));
        expense.setCategory(category);
        return expenseRepository.save(expense);
    }

    public Expense updateExpense(Long id, Expense updatedExpense) {
        try {
            Expense existingExpense = expenseRepository.getReferenceById(id);
            existingExpense.setDescription(updatedExpense.getDescription());
            return expenseRepository.save(existingExpense);
        } catch (EntityNotFoundException e) {
            throw new RuntimeException("Expense with ID " + id + " not found");
        }
    }

    public void deleteCategory(Long id) {
        expenseRepository.deleteById(id);
    }

}
