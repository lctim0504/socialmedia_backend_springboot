package com.example.demo.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.User;

@Repository
public interface AuthDao extends JpaRepository<User, Long> {

    // User save(User user);
    @Override
    <UserS extends User> UserS save(UserS user);

    User findByUsernameAndPassword(String username, String password);

}
