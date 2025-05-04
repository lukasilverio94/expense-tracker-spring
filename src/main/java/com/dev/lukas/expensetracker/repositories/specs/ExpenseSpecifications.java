package com.dev.lukas.expensetracker.repositories.specs;

import com.dev.lukas.expensetracker.domain.models.Expense;
import org.springframework.data.jpa.domain.Specification;

public class ExpenseSpecifications {

    public static Specification<Expense> descriptionLike(String description) {
        return
                (root, query, cb)
                        -> cb.like(cb.lower(root.get("description")), "%" + description.toLowerCase() + "%");
    }

    public static Specification<Expense> categoryEquals(String categoryName) {
        return (root, query, cb) -> cb.equal(root.get("category").get("name"), categoryName);
    }
}
