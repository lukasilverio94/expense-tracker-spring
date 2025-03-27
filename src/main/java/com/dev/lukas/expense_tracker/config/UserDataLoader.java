package com.dev.lukas.expense_tracker.config;

import com.dev.lukas.expense_tracker.entities.User;
import com.dev.lukas.expense_tracker.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class UserDataLoader implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) {
        loadUserData();
    }

    private void loadUserData() {
        if (userRepository.count() == 0) {
            User user1 = new User(null, "Maria Brown", "maria@test.com", "123456");
            User user2 = new User(null, "Jose Silverio", "jose@test.com", "132166");
            User user3 = new User(null, "Carol Lune", "luneca@test.com", "1fDadd56");
            User user4 = new User(null, "Lucas Jansen", "lucas@test.com", "pass789");
            User user5 = new User(null, "Emma Visser", "emma@test.com", "securePass1");
            User user6 = new User(null, "Daan de Vries", "daan@test.com", "welcome123");

            userRepository.saveAll(Arrays.asList(user1, user2, user3, user4, user5, user6));
        }
        System.out.println(userRepository.count());
    }
}
