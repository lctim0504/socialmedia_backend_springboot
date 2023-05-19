package com.example.demo.user.search;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.User;

@Repository
public interface UserSearchDao extends JpaRepository<User, Integer> {

    List<User> findByUsernameContaining(String usernameQuery);
}