package com.dev.lukas.expensetracker.services;

import java.util.List;
import java.util.Optional;

import com.dev.lukas.expensetracker.data.dtos.ExpenseDto;
import com.dev.lukas.expensetracker.data.mapper.ExpenseMapper;
import com.dev.lukas.expensetracker.data.model.ExpenseRequestModel;
import com.dev.lukas.expensetracker.data.entity.Category;
import com.dev.lukas.expensetracker.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.dev.lukas.expensetracker.data.entity.Expense;
import com.dev.lukas.expensetracker.repositories.ExpenseRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    private final CategoryRepository categoryRepository;
    private final ExpenseMapper expenseMapper;

    public List<Expense> findAll() {
        return expenseRepository.findAll();
    }

    public Optional<Expense> findById(Long id) {
        return expenseRepository.findById(id);
    }

    public Expense save(ExpenseDto expense) {
        Category category = categoryRepository.findById(expense.getCategory().getId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        expense.setCategory(category);
        Expense newExpense = this.expenseMapper.dtoToEntity(expense);
        return expenseRepository.save(newExpense);
    }

    public Expense update(Long id, ExpenseRequestModel dto) {
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
