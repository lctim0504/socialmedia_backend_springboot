package com.example.demo.components.user.setting;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.UserSettings;

@RestController
@RequestMapping("/api/users/{userId}/settings")
public class UserSettingsController {

    private final UserSettingsService userSettingsService;

    public UserSettingsController(UserSettingsService userSettingsService) {
        this.userSettingsService = userSettingsService;
    }

    @GetMapping
    public UserSettings getUserSettings(@PathVariable int userId) {
        return userSettingsService.getUserSettings(userId);
    }

    @PutMapping
    public UserSettings updateUserSettings(@PathVariable int userId, @RequestBody UserSettings userSettings) {
        return userSettingsService.updateUserSettings(userId, userSettings);
    }
}
