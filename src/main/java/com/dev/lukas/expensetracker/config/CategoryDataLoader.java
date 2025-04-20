package com.dev.lukas.expensetracker.config;

import com.dev.lukas.expensetracker.data.entity.Category;
import com.dev.lukas.expensetracker.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class CategoryDataLoader implements CommandLineRunner {
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public void run(String... args) throws Exception {
        loadCategoryData();
    }

    private void loadCategoryData() {
        if (categoryRepository.count() == 0) {
            Category cat1 = new Category(null, "Supermarket");
            Category cat2 = new Category(null, "Restaurant");
            Category cat3 = new Category(null, "For Fun");
            Category cat4 = new Category(null, "Housing Rent/Mortgage");
            Category cat5 = new Category(null, "Utilities");
            Category cat6 = new Category(null, "Public Transport");
            Category cat7 = new Category(null, "Bike Maintenance");
            Category cat8 = new Category(null, "Healthcare & Insurance");
            Category cat9 = new Category(null, "Internet & Mobile");
            Category cat10 = new Category(null, "Subscriptions");
            Category cat11 = new Category(null, "Groceries");
            Category cat12 = new Category(null, "Clothing & Shoes");
            Category cat13 = new Category(null, "Gifts & Donations");
            Category cat14 = new Category(null, "Sports & Gym");
            Category cat15 = new Category(null, "Travel & Holidays");
            Category cat16 = new Category(null, "Education & Courses");
            Category cat17 = new Category(null, "Work-related Expenses");
            Category cat18 = new Category(null, "Coffee & Snacks");
            Category cat19 = new Category(null, "Household Items");
            Category cat20 = new Category(null, "Pets");

            categoryRepository.saveAll(Arrays.asList(
                    cat1, cat2, cat3, cat4, cat5, cat6, cat7, cat8, cat9, cat10,
                    cat11, cat12, cat13, cat14, cat15, cat16, cat17, cat18, cat19, cat20
            ));
        }
        System.out.println(categoryRepository.count());
    }

}
