package com.dev.lukas.expensetracker.services;

import com.dev.lukas.expensetracker.domain.models.User;
import com.dev.lukas.expensetracker.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public User insertUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(Long id, User userDetails) {
        Optional<User> existingUserOpt = userRepository.findById(id);
        if (existingUserOpt.isPresent()) {
            User existingUser = existingUserOpt.get();

            existingUser.setUsername(userDetails.getUsername());
            existingUser.setEmail(userDetails.getEmail());

            return userRepository.save(existingUser);
        }
        // later i will make a custom exception to throw instead return null
        return null;
    }

    public void deleteUser(Long id) {
        Optional<User> existingUserOpt = userRepository.findById(id);
        if(existingUserOpt.isPresent()){
            userRepository.deleteById(id);
        } else {
            throw new RuntimeException("User not found with ID: " + id);
        }
    }
}
