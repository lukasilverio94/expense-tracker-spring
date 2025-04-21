package com.dev.lukas.expensetracker.services;

import com.dev.lukas.expensetracker.controllers.mappers.ExpenseDTOMapper;
import com.dev.lukas.expensetracker.domain.dtos.ExpenseDTO;
import com.dev.lukas.expensetracker.domain.dtos.ExpenseResponseDTO;
import com.dev.lukas.expensetracker.domain.models.Category;
import com.dev.lukas.expensetracker.domain.models.Expense;
import com.dev.lukas.expensetracker.repositories.CategoryRepository;
import com.dev.lukas.expensetracker.repositories.ExpenseRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final CategoryRepository categoryRepository;
    private final ExpenseDTOMapper expenseDTOMapper;

    public Expense save(ExpenseDTO dto) {
        Category category = categoryRepository.findById(dto.categoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Expense expense = expenseDTOMapper.toEntity(dto, category);
        return expenseRepository.save(expense);
    }

    public Optional<ExpenseResponseDTO> findById(Long id) {
        return expenseRepository.findById(id)
                .map(expenseDTOMapper::toGetExpenseDTO);
    }

    public List<ExpenseResponseDTO> findAll() {
        return expenseRepository.findAll()
                .stream()
                .map(expenseDTOMapper::toGetExpenseDTO)
                .toList();
    }

    public void update(Long id, ExpenseDTO dto) {
        Expense existingExpense = expenseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Expense with ID " + id + " not found"));

        Category category = categoryRepository.findById(dto.categoryId())
                .orElseThrow(() -> new EntityNotFoundException("Category with ID " + dto.categoryId() + " not found"));

        existingExpense.setCategory(category);
        existingExpense.setDescription(dto.description());
        existingExpense.setValue(dto.value());

        expenseRepository.save(existingExpense);

    }

    public void delete(Long id) {
        expenseRepository.deleteById(id);
    }

    public void deleteAll(){
        expenseRepository.deleteAll();
    }



}
