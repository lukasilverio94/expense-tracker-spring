package com.dev.lukas.expensetracker.controllers;

import com.dev.lukas.expensetracker.data.dtos.CategoryDto;
import com.dev.lukas.expensetracker.data.mapper.CategoryMapper;
import com.dev.lukas.expensetracker.data.model.CategoryRequestModel;
import com.dev.lukas.expensetracker.data.model.CategoryResponseModel;
import com.dev.lukas.expensetracker.services.CategoryService;
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
    private final CategoryMapper mapper;

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody CategoryRequestModel model) {
        var dto = this.mapper.modelToDto(model);
        this.service.save(dto);
        URI locationHeader = generateLocationHeader(dto.id());
        return ResponseEntity.created(locationHeader).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseModel> findById(@PathVariable Long id) {
        return this.service.findById(id)
                .map(dto -> ResponseEntity.ok(this.mapper.dtoToModel(dto)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponseModel>> findAll() {
        var categories = this.service.findAll().stream()
                .map(this.mapper::dtoToModel)
                .toList();
        if (categories.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(categories);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseModel> update(@PathVariable Long id, @RequestBody CategoryRequestModel model) {
        var dto = this.mapper.modelToDto(model);

        CategoryDto updatedCategoryDto = this.service.update(dto);
        if (updatedCategoryDto != null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
