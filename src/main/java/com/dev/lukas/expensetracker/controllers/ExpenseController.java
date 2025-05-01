package com.dev.lukas.expensetracker.controllers;

import com.dev.lukas.expensetracker.controllers.mappers.ExpenseDTOMapper;
import com.dev.lukas.expensetracker.domain.dtos.ExpenseDTO;
import com.dev.lukas.expensetracker.domain.dtos.ExpenseResponseDTO;
import com.dev.lukas.expensetracker.domain.dtos.TotalExpensesDTO;
import com.dev.lukas.expensetracker.services.ExpenseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/expenses")
@RequiredArgsConstructor
public class ExpenseController implements GenericController {

    private final ExpenseService expenseService;

    private final ExpenseDTOMapper mapper;

    @PostMapping
    public ResponseEntity<Void> insertExpense(@RequestBody @Valid ExpenseDTO dto) {
        Long newId = expenseService.save(dto).id();
        URI location = generateLocationHeader(newId);
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExpenseResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.of(expenseService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<ExpenseResponseDTO>> search(@RequestParam(value = "description", required = false) String description) {
        var result = expenseService.searchExpenses(description);
        var list = result
                .stream()
                .map(mapper::toGetExpenseDTO)
                .toList();

        return ResponseEntity.ok(list);
    }


//    @GetMapping
//    public ResponseEntity<List<ExpenseResponseDTO>> findAll() {
//        return ResponseEntity.ok(expenseService.findAll());
//    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody @Valid ExpenseDTO dto) {
        expenseService.update(id, dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        expenseService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAll() {
        expenseService.deleteAll();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/total")
    public ResponseEntity<TotalExpensesDTO> getTotalSumExpenses(){
        BigDecimal total = expenseService.getTotalSumExpenses();
        return ResponseEntity.ok(new TotalExpensesDTO(total));
    }

}
