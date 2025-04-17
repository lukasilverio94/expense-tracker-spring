package com.dev.lukas.expense_tracker.controllers;

import com.dev.lukas.expense_tracker.dtos.CategoryRequestDTO;
import com.dev.lukas.expense_tracker.dtos.CategoryResponseDTO;
import com.dev.lukas.expense_tracker.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/categories")
@RequiredArgsConstructor
public class CategoryController implements GenericController {

    private final CategoryService service;

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody CategoryRequestDTO dto) {
        service.save(dto);
        URI locationHeader = generateLocationHeader(dto.id());
        return ResponseEntity.created(locationHeader).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> findById(@PathVariable Long id) {
        return service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponseDTO>> findAll() {
      List<CategoryResponseDTO> categories =  service.findAll();
      if (categories.isEmpty()){
          return ResponseEntity.noContent().build();
      }
      return ResponseEntity.ok(categories);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> update(@PathVariable Long id, @RequestBody CategoryRequestDTO dto) {
        CategoryResponseDTO updatedCategoryDto = service.update(id, dto);
        if (updatedCategoryDto != null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
            service.delete(id);
            return ResponseEntity.noContent().build();
    }
}
