package com.dev.lukas.expensetracker.services;

import com.dev.lukas.expensetracker.controllers.mappers.CategoryDTOMapper;
import com.dev.lukas.expensetracker.domain.dtos.CategoryDTO;
import com.dev.lukas.expensetracker.domain.models.Category;
import com.dev.lukas.expensetracker.exceptions.CategoryExistsException;
import com.dev.lukas.expensetracker.exceptions.EntityNotFoundException;
import com.dev.lukas.expensetracker.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository repository;
    private final CategoryDTOMapper categoryDTOMapper;

    public void save(CategoryDTO dto) {
        Optional<Category> existingCategory = repository.findByName(dto.name());

        if (existingCategory.isPresent()){
            throw new CategoryExistsException("Category " + dto.name() + " already exists!");
        }

        Category newCategory = new Category();
        newCategory.setName(dto.name());
        repository.save(newCategory);
    }

    public Optional<CategoryDTO> findById(Long id) {
        return repository.findById(id).map(categoryDTOMapper);
    }

    public List<CategoryDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(categoryDTOMapper).toList();
    }

    public CategoryDTO update(Long id, CategoryDTO dto) {
       Category existingCategory = repository.findById(id)
               .orElseThrow(() -> new EntityNotFoundException("Category not found"));

       Optional<Category> categoryWithSameName = repository.findByName(dto.name());

       if (categoryWithSameName.isPresent() && !categoryWithSameName.get().getId().equals(id)){
           throw new CategoryExistsException("Category with name " + dto.name() + " already exists");
       }

       existingCategory.setName(dto.name());
       Category updatedCategory = repository.save(existingCategory);
       return categoryDTOMapper.apply(updatedCategory);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

}
