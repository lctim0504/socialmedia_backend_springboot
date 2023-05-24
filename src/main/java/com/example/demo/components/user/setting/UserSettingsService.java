package com.example.demo.components.user.setting;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.components.user.UserDao;
import com.example.demo.enums.Theme;
import com.example.demo.model.User;
import com.example.demo.model.UserSettings;

@Service
public class UserSettingsService {

    private final UserSettingsDao UserSettingsDao;
    private final UserDao userDao;

    public UserSettingsService(UserSettingsDao UserSettingsDao, UserDao userDao) {
        this.UserSettingsDao = UserSettingsDao;
        this.userDao = userDao;
    }

    public UserSettings getUserSettings(int userId) {
        User user = userDao.findById(userId).orElse(null);
        if (user != null) {
            return UserSettingsDao.findByUser(user).orElse(null);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
    }

    public UserSettings updateUserSettings(int userId, UserSettings newSettings) {
        User user = userDao.findById(userId).orElse(null);
        UserSettings existingSettings = UserSettingsDao.findByUser(user).orElse(null);

        if (user == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        if (existingSettings == null)
            return createUserSettings(userId, newSettings);
        else {
            existingSettings.setTheme(newSettings.getTheme());
            existingSettings.setNotificationsEnabled(newSettings.isNotificationsEnabled());
            existingSettings.setUpdatedAt(LocalDateTime.now());

            return UserSettingsDao.save(existingSettings);
        }
    }

    public UserSettings createUserSettings(int userId, UserSettings newSettings) {
        User user = userDao.findById(userId).orElse(null);
        UserSettings existingSettings = UserSettingsDao.findByUser(user).orElse(null);

        if (user == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        if (existingSettings != null)
            return updateUserSettings(userId, newSettings);
        else {
            newSettings.setTheme(Theme.LIGHT);
            newSettings.setNotificationsEnabled(true);
            newSettings.setCreatedAt(LocalDateTime.now());
            return UserSettingsDao.save(newSettings);
        }
    }
}
