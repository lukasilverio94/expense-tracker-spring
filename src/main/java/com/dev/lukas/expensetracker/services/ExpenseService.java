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
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

import static com.dev.lukas.expensetracker.repositories.specs.ExpenseSpecifications.descriptionLike;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final CategoryRepository categoryRepository;
    private final ExpenseDTOMapper expenseDTOMapper;

    public ExpenseResponseDTO save(ExpenseDTO dto) {
        Category category = categoryRepository.findById(dto.categoryId())
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));

        Expense expense = expenseDTOMapper.toEntity(dto, category);
        Expense savedExpense = expenseRepository.save(expense);
        return expenseDTOMapper.toGetExpenseDTO(savedExpense);
    }

    public Optional<ExpenseResponseDTO> findById(Long id) {
        return expenseRepository.findById(id)
                .map(expenseDTOMapper::toGetExpenseDTO);
    }

    public List<Expense> searchExpenses(String description){

        Specification<Expense> specs = Specification
                .where((root, query, cb) -> cb.conjunction());

        if (description != null){
            specs = specs.and(descriptionLike(description));
        }

        return expenseRepository.findAll(specs);
    }

    public List<ExpenseResponseDTO> findAll() {
        return expenseRepository.findAllByOrderByCreatedAtDesc()
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

    public BigDecimal getTotalSumExpenses(){
        BigDecimal total = expenseRepository.findTotalExpenses();
        return (total != null ? total : BigDecimal.ZERO).setScale(2, RoundingMode.HALF_UP);
    }

}
