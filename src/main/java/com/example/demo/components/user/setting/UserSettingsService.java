package com.example.demo.components.user.setting;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.example.demo.components.user.UserDao;
import com.example.demo.model.User;
import com.example.demo.model.UserSettings;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserSettingsService {

    private final UserSettingsDao UserSettingsDao;
    private final UserDao userDao;

    public UserSettingsService(UserSettingsDao UserSettingsDao, UserDao userDao) {
        this.UserSettingsDao = UserSettingsDao;
        this.userDao = userDao;
    }

    public UserSettings getUserSettings(int userId) {
        User user = userDao.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));
        return UserSettingsDao.findByUser(user)
                .orElseThrow(() -> new EntityNotFoundException("User settings not found for user ID: " + userId));
    }

    public UserSettings updateUserSettings(int userId, UserSettings newSettings) {
        User user = userDao.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));

        UserSettings existingSettings = UserSettingsDao.findByUser(user)
                .orElseThrow(() -> new EntityNotFoundException("User settings not found for user ID: " + userId));

        // Update the settings with new values
        existingSettings.setTheme(newSettings.getTheme());
        existingSettings.setNotificationsEnabled(newSettings.isNotificationsEnabled());

        // Update the 'updatedAt' timestamp
        existingSettings.setUpdatedAt(LocalDateTime.now());

        // Save the updated settings
        return UserSettingsDao.save(existingSettings);
    }
}
