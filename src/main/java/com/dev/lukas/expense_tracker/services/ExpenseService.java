package com.dev.lukas.expense_tracker.services;

import java.util.List;
import java.util.Optional;

import com.dev.lukas.expense_tracker.dtos.ExpenseRequestDTO;
import com.dev.lukas.expense_tracker.models.Category;
import com.dev.lukas.expense_tracker.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.dev.lukas.expense_tracker.models.Expense;
import com.dev.lukas.expense_tracker.repositories.ExpenseRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    private final CategoryRepository categoryRepository;

    public List<Expense> findAll() {
        return expenseRepository.findAll();
    }

    public Optional<Expense> findById(Long id) {
        return expenseRepository.findById(id);
    }

    public Expense save(Expense expense) {
        Category category = categoryRepository.findById(expense.getCategory().getId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        expense.setCategory(category);
        return expenseRepository.save(expense);
    }

    public Expense update(Long id, ExpenseRequestDTO dto) {
        Expense existingExpense = expenseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Expense with ID " + id + " not found"));

        Category category = categoryRepository.findById(dto.categoryId())
                .orElseThrow(() -> new EntityNotFoundException("Category with ID " + dto.categoryId() + " not found"));

        existingExpense.setCategory(category);
        existingExpense.setDescription(dto.description());
        existingExpense.setValue(dto.value());

        return expenseRepository.save(existingExpense);

    }

    public void delete(Long id) {
        expenseRepository.deleteById(id);
    }

    public void deleteAll(){
        expenseRepository.deleteAll();
    }

}
