package com.example.demo.components.user.setting;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    // 取得使用者設定
    @GetMapping
    public UserSettings getUserSettings(@PathVariable int userId) {
        return userSettingsService.getUserSettings(userId);
    }

    // 更新使用者設定
    @PutMapping
    public UserSettings updateUserSettings(@PathVariable int userId, @RequestBody UserSettings userSettings) {
        return userSettingsService.updateUserSettings(userId, userSettings);
    }

    // 新增使用者設定
    @PostMapping
    public UserSettings createUserSettings(@PathVariable int userId, @RequestBody UserSettings userSettings) {
        return userSettingsService.createUserSettings(userId, userSettings);
    }
}
