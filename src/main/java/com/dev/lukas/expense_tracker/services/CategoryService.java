package com.dev.lukas.expense_tracker.services;

import com.dev.lukas.expense_tracker.entities.Category;
import com.dev.lukas.expense_tracker.repositories.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

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
        try{
            Category existingCategory = categoryRepository.getReferenceById(id);
            existingCategory.setName(updatedCategory.getName());
            return categoryRepository.save(existingCategory);
        } catch (EntityNotFoundException e){
            throw new RuntimeException("Category with ID " + id + " not found");
        }
    }

    public void deleteCategory(Long id){
        categoryRepository.deleteById(id);
    }
}
