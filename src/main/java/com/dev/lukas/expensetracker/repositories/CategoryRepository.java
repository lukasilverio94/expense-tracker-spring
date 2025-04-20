package com.dev.lukas.expensetracker.repositories;

import com.dev.lukas.expensetracker.domain.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
