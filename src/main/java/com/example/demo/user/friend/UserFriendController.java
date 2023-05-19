package com.example.demo.user.friend;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UserDto;

@RestController
@RequestMapping("/api/users/{userId}")
public class UserFriendController {

    private final UserFriendService userService;

    public UserFriendController(UserFriendService userService) {
        this.userService = userService;
    }
    // 取得使用者的好友清單
    @GetMapping("/friends")
    public ResponseEntity<List<UserDto>> getFriends(@PathVariable("userId") int userId) {
        List<UserDto> friends = userService.getFriends(userId);
        return ResponseEntity.ok(friends);
    }

    // 加入好友
    @PostMapping("/friends")
    public ResponseEntity<String> addFriend(@PathVariable("userId") int userId,
            @RequestBody Map<String, Integer> request) {
        int friendId = request.get("friendId");
        userService.addFriend(userId, friendId);
        return ResponseEntity.ok("Friend added successfully");
    }

    // 移除好友
    @DeleteMapping("/friends/{friendId}")
    public ResponseEntity<String> removeFriend(@PathVariable("userId") int userId,
            @PathVariable("friendId") int friendId) {
        userService.removeFriend(userId, friendId);
        return ResponseEntity.ok("Friend removed successfully");
    }
}
