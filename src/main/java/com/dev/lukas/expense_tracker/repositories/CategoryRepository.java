package com.dev.lukas.expense_tracker.repositories;

import com.dev.lukas.expense_tracker.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
