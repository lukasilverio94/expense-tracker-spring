package com.dev.lukas.expensetracker.controllers;

import com.dev.lukas.expensetracker.data.dtos.ExpenseDto;
import com.dev.lukas.expensetracker.data.mapper.ExpenseMapper;
import com.dev.lukas.expensetracker.data.model.ExpenseRequestModel;
import com.dev.lukas.expensetracker.data.model.ExpenseResponseModel;
import com.dev.lukas.expensetracker.data.entity.Expense;
import com.dev.lukas.expensetracker.repositories.CategoryRepository;
import com.dev.lukas.expensetracker.services.ExpenseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/expenses")
@RequiredArgsConstructor
public class ExpenseController implements GenericController {

    private final ExpenseService expenseService;
    private final ExpenseMapper mapper;
    private final CategoryRepository categoryRepository;


    @PostMapping
    public ResponseEntity<Void> insertExpense(@RequestBody @Valid ExpenseRequestModel model) {
        ExpenseDto expense = this.mapper.modelToDto(model);
        expenseService.save(expense);
        URI locationUrlHeader = generateLocationHeader(expense.getId());
        return ResponseEntity.created(locationUrlHeader).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExpenseResponseModel> findById(@PathVariable Long id) {
        return expenseService.findById(id)
                .map(mapper::entityToDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<ExpenseResponseModel>> findAll() {
        List<ExpenseResponseModel> expenses = expenseService.findAll()
                .stream()
                .map(mapper::toGetExpenseDTO)
                .toList();
        return ResponseEntity.ok().body(expenses);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        System.out.println();
        Optional<Expense> expenseOptional = expenseService.findById(id);
        if (expenseOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        expenseService.delete(expenseOptional.get().getId());
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody @Valid ExpenseRequestModel dto) {
        expenseService.update(id, dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAll(){
        expenseService.deleteAll();
        return ResponseEntity.noContent().build();
    }

}
