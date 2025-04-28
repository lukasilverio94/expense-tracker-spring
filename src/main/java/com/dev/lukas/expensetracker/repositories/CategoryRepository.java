package com.dev.lukas.expensetracker.repositories;

import com.dev.lukas.expensetracker.domain.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByName(String name);
}
