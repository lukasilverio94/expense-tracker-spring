package com.dev.lukas.expense_tracker.config;

import com.dev.lukas.expense_tracker.entities.User;
import com.dev.lukas.expense_tracker.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {

        User user1 = new User(null, "Maria Brown", "maria@test.com", "123456");
        User user2 = new User(null, "Jose Silverio", "jose@test.com", "132166");
        User user3 = new User(null, "Carol Lune", "luneca@test.com", "1fDadd56");

        userRepository.saveAll(Arrays.asList(user1, user2, user3));
    }
}
