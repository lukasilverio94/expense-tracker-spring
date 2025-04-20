package com.dev.lukas.expensetracker.repositories;

import com.dev.lukas.expensetracker.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
