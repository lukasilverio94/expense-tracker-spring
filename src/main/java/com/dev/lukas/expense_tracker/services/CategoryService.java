package com.dev.lukas.expense_tracker.services;

import com.dev.lukas.expense_tracker.dtos.CategoryRequestDTO;
import com.dev.lukas.expense_tracker.dtos.CategoryResponseDTO;
import com.dev.lukas.expense_tracker.models.Category;
import com.dev.lukas.expense_tracker.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository repository;

    public void save(CategoryRequestDTO dto) {
        Category category = convertToEntity(dto);
        Category savedCategory = repository.save(category);
        convertToDto(savedCategory);
    }

    public Optional<CategoryResponseDTO> findById(Long id) {
        return repository.findById(id).map(this::convertToDto);
    }

    public List<CategoryResponseDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public CategoryResponseDTO update(Long id, CategoryRequestDTO dto) {
       Optional<Category> existingCategoryOptional = repository.findById(id);
       if(existingCategoryOptional.isEmpty()){
           return null;
       }

       Category existingCategory = existingCategoryOptional.get();
       existingCategory.setName(dto.name());

       Category updatedCategory = repository.save(existingCategory);
       return convertToDto(updatedCategory);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    // Conversion methods between DTO and Entity
    private CategoryResponseDTO convertToDto(Category category) {
        return new CategoryResponseDTO(category.getName());
    }

    private Category convertToEntity(CategoryRequestDTO dto){
        Category category = new Category();
        category.setName(dto.name());
        return category;
    }



}
