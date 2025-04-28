package com.dev.lukas.expensetracker.services;

import com.dev.lukas.expensetracker.data.dtos.CategoryDto;
import com.dev.lukas.expensetracker.data.mapper.CategoryMapper;
import com.dev.lukas.expensetracker.data.model.CategoryRequestModel;
import com.dev.lukas.expensetracker.data.model.CategoryResponseModel;
import com.dev.lukas.expensetracker.data.entity.Category;
import com.dev.lukas.expensetracker.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository repository;
    private final CategoryMapper categoryMapper;

    public void save(CategoryDto dto) {
        Category category = this.categoryMapper.dtoToEntity(dto);
        this.repository.save(category);
    }

    public Optional<CategoryDto> findById(Long id) {
        return this.repository.findById(id).map(this.categoryMapper::entityToDto);
    }

    public List<CategoryDto> findAll() {
        return this.repository.findAll()
                .stream()
                .map(this.categoryMapper::entityToDto)
                .collect(Collectors.toList());
    }

    public CategoryDto update(CategoryDto dto) {
       Optional<Category> existingCategoryOptional = repository.findById(dto.id());
       if(existingCategoryOptional.isEmpty()){
           // lançar exceção e tratar no exception handler
           return null;
       }

       Category existingCategory = existingCategoryOptional.get();
       existingCategory.setName(dto.name());

       Category updatedCategory = this.repository.save(existingCategory);
       return this.categoryMapper.entityToDto(updatedCategory);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    // Conversion methods between DTO and Entity
    private CategoryResponseModel convertToDto(Category category) {
        return new CategoryResponseModel(category.getName());
    }

    private Category convertToEntity(CategoryRequestModel dto){
        Category category = new Category();
        category.setName(dto.name());
        return category;
    }



}
