package com.dev.lukas.expensetracker.services;

import com.dev.lukas.expensetracker.controllers.mappers.CategoryDTOMapper;
import com.dev.lukas.expensetracker.domain.dtos.CategoryDTO;
import com.dev.lukas.expensetracker.domain.models.Category;
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
       Optional<Category> existingCategoryOptional = repository.findById(id);
       if(existingCategoryOptional.isEmpty()){
           return null;
       }

       Category existingCategory = existingCategoryOptional.get();
       existingCategory.setName(dto.name());

       Category updatedCategory = repository.save(existingCategory);
       return categoryDTOMapper.apply(updatedCategory);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

}
