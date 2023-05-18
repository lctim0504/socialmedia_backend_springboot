package com.example.demo.user.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);

    User findByEmail(String email);

    // List<User> findByAgeGreaterThan(int age);

    // List<User> findByFirstNameContainsIgnoreCase(String keyword);

    // 其他自定義查詢方法...
}
