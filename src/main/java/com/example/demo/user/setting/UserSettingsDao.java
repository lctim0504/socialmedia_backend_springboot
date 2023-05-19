package com.example.demo.user.setting;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.User;
import com.example.demo.model.UserSettings;

@Repository
public interface UserSettingsDao extends JpaRepository<UserSettings, Integer> {

    Optional<UserSettings> findByUser(User user);
}
