package com.dev.lukas.expensetracker.controllers;

import com.dev.lukas.expensetracker.domain.dtos.ExpenseDTO;
import com.dev.lukas.expensetracker.domain.dtos.ExpenseResponseDTO;
import com.dev.lukas.expensetracker.services.ExpenseService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/expenses")
@RequiredArgsConstructor
public class ExpenseController implements GenericController {

    private final ExpenseService expenseService;

    @PostMapping
    public ResponseEntity<Void> insertExpense(@RequestBody @Valid ExpenseDTO dto) {
        Long newId = expenseService.save(dto).getId();
        URI location = generateLocationHeader(newId);
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExpenseResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.of(expenseService.findDtoById(id));
    }

    @GetMapping
    public ResponseEntity<List<ExpenseResponseDTO>> findAll() {
        return ResponseEntity.ok(expenseService.findAllDtos());
    }

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

}
