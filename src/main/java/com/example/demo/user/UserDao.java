package com.example.demo.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.User;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {

    User findByUsername(String username);

    User findByEmail(String email);
}
