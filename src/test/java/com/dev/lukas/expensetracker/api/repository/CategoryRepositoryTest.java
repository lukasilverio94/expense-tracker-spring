package com.dev.lukas.expensetracker.api.repository;

import com.dev.lukas.expensetracker.domain.models.Category;
import com.dev.lukas.expensetracker.repositories.CategoryRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository repositoryTest;

    @Test
    public void save_WhenValidCategoryExists_ReturnsSavedCategory() {

        // arrange
        Category category = Category.builder()
                .name("others")
                .build();

        // act
        Category savedCategory = repositoryTest.save(category);

        // assert
        Assertions.assertThat(savedCategory).isNotNull();
        Assertions.assertThat(savedCategory.getId()).isGreaterThan(0);
    }

    @Test
    public void findById_WhenCategoryExists_ReturnsCategory() {
        // arrange
        Category category = Category.builder()
                .name("others")
                .build();
        Category savedCategory = repositoryTest.save(category);

        // act
        Category foundCategory = repositoryTest.findById(savedCategory.getId()).orElse(null);

        // assert
        Assertions.assertThat(foundCategory).isNotNull();
        Assertions.assertThat(foundCategory.getId()).isEqualTo(savedCategory.getId());
        Assertions.assertThat(foundCategory.getName()).isEqualTo(savedCategory.getName());
    }

    @Test
    public void findAll_WhenCalled_ReturnsAllCategories() {
        // arrange
        Category category1 = Category.builder().name("Food").build();
        Category category2 = Category.builder().name("Travel").build();
        repositoryTest.save(category1);
        repositoryTest.save(category2);

        // act
        List<Category> categories = repositoryTest.findAll();

        // assert
        Assertions.assertThat(categories).isNotNull();
        Assertions.assertThat(categories.size()).isEqualTo(2);
        Assertions.assertThat(categories).extracting(Category::getName)
                .containsExactly("Food", "Travel");

    }

    @Test
    public void updateById_WhenCategoryExists_ReturnsCategory() {
        // arrange
        Category category = Category.builder().name("Old Name").build();
        Category savedCategory = repositoryTest.save(category);

        // act
        savedCategory.setName("New Name");
        Category updatedCategory = repositoryTest.save(savedCategory);

        // assert
        Assertions.assertThat(updatedCategory).isNotNull();
        Assertions.assertThat(updatedCategory.getId()).isEqualTo(savedCategory.getId());
        Assertions.assertThat(updatedCategory.getName()).isEqualTo("New Name");
    }

    @Test
    public void deleteById_WhenCategoryExists() {
        // arrange
        Category category = Category.builder().name("Delete this category").build();
        Category savedCategory = repositoryTest.save(category);

        // act
        repositoryTest.deleteById(savedCategory.getId());

        // assert
        boolean exists = repositoryTest.existsById(savedCategory.getId());
        Assertions.assertThat(exists).isFalse();
    }
}
