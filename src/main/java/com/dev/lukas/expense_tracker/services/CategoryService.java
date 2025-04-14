package com.dev.lukas.expense_tracker.services;

import com.dev.lukas.expense_tracker.models.Category;
import com.dev.lukas.expense_tracker.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> findAll(){
        return categoryRepository.findAll();
    }

    public Optional<Category> findById(Long id){
        return categoryRepository.findById(id);
    }

    public Category insertCategory(Category cat){
        return categoryRepository.save(cat);
    }

    public Category updateCategory(Long id, Category updatedCategory){
            Category existingCategory = categoryRepository.getReferenceById(id);
            existingCategory.setName(updatedCategory.getName());
            return categoryRepository.save(existingCategory);
    }

    public void deleteCategory(Long id){
        categoryRepository.deleteById(id);
    }
}
