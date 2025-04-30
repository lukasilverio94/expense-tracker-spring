package com.dev.lukas.expensetracker.config;

import com.dev.lukas.expensetracker.domain.models.Category;
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
    public void run(String... args) {
        loadCategoryData();
    }

    private void loadCategoryData() {
        if (categoryRepository.count() == 0) {
            Category cat1 = new Category("Supermarket");
            Category cat2 = new Category("Restaurant");
            Category cat3 = new Category("For Fun");
            Category cat4 = new Category("Housing Rent/Mortgage");
            Category cat5 = new Category("Utilities");
            Category cat6 = new Category("Public Transport");
            Category cat7 = new Category("Bike Maintenance");
            Category cat8 = new Category("Healthcare & Insurance");
            Category cat9 = new Category("Internet & Mobile");
            Category cat10 = new Category("Subscriptions");
            Category cat11 = new Category("Groceries");
            Category cat12 = new Category("Clothing & Shoes");
            Category cat13 = new Category("Gifts & Donations");
            Category cat14 = new Category("Sports & Gym");
            Category cat15 = new Category("Travel & Holidays");
            Category cat16 = new Category("Education & Courses");
            Category cat17 = new Category("Work");
            Category cat18 = new Category("Coffee & Snacks");
            Category cat19 = new Category("Household Items");
            Category cat20 = new Category("Pets");
            Category cat21 = new Category("Savings");

            categoryRepository.saveAll(Arrays.asList(
                    cat1, cat2, cat3, cat4, cat5, cat6, cat7, cat8, cat9, cat10,
                    cat11, cat12, cat13, cat14, cat15, cat16, cat17, cat18, cat19, cat20, cat21
            ));
        }
        System.out.println(categoryRepository.count());
    }

}
