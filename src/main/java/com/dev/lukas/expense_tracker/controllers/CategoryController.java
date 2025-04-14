package com.dev.lukas.expense_tracker.controllers;

import com.dev.lukas.expense_tracker.models.Category;
import com.dev.lukas.expense_tracker.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/categories")
@RequiredArgsConstructor
public class CategoryController {

    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Category> insertCategory(@RequestBody Category cat) {
        Category category = categoryService.insertCategory(cat);
        return ResponseEntity.ok().body(category);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> findById(@PathVariable Long id) {
        return categoryService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Category>> findAll() {
        List<Category> categories = categoryService.findAll();
        return ResponseEntity.ok().body(categories);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category catDetails) {
        Category updatedCategory = categoryService.updateCategory(id, catDetails);
        if (updatedCategory != null) {
            return ResponseEntity.ok(updatedCategory);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        try {
            categoryService.deleteCategory(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
