package com.dev.lukas.expense_tracker.controllers;

import com.dev.lukas.expense_tracker.controllers.mappers.ExpenseMapper;
import com.dev.lukas.expense_tracker.dtos.GetExpenseDTO;
import com.dev.lukas.expense_tracker.dtos.InsertExpenseDTO;
import com.dev.lukas.expense_tracker.models.Expense;
import com.dev.lukas.expense_tracker.repositories.CategoryRepository;
import com.dev.lukas.expense_tracker.services.ExpenseService;
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
    public ResponseEntity<Void> insertExpense(@RequestBody @Valid InsertExpenseDTO dto) {
        Expense expense = mapper.toExpense(dto);
        expenseService.save(expense);
        URI locationUrlHeader = generateLocationHeader(expense.getId());
        return ResponseEntity.created(locationUrlHeader).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetExpenseDTO> findById(@PathVariable Long id) {
        return expenseService.findById(id)
                .map(mapper::toGetExpenseDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<GetExpenseDTO>> findAll() {
        List<GetExpenseDTO> expenses = expenseService.findAll()
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
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody @Valid InsertExpenseDTO dto) {
        expenseService.update(id, dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAll(){
        expenseService.deleteAll();
        return ResponseEntity.noContent().build();
    }

}
