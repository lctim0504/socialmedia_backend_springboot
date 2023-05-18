package com.example.demo.user;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UserDto;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") int id) {
        UserDto user = userService.getUserById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        UserDto createdUser = userService.createUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") int id, @RequestBody UserDto userDto) {
        UserDto updatedUser = userService.updateUser(id, userDto);
        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") int id) {
        boolean deleted = userService.deleteUser(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 取得使用者的好友清單
    @GetMapping("/{userId}/friends")
    public ResponseEntity<List<UserDto>> getFriends(@PathVariable("userId") int userId) {
        List<UserDto> friends = userService.getFriends(userId);
        return ResponseEntity.ok(friends);
    }

    // 加入好友
    @PostMapping("/{userId}/friends")
    public ResponseEntity<String> addFriend(@PathVariable("userId") int userId,
            @RequestBody Map<String, Integer> request) {
        int friendId = request.get("friendId");
        userService.addFriend(userId, friendId);
        return ResponseEntity.ok("Friend added successfully");
    }

    // 移除好友
    @DeleteMapping("/{userId}/friends/{friendId}")
    public ResponseEntity<String> removeFriend(@PathVariable("userId") int userId,
            @PathVariable("friendId") int friendId) {
        userService.removeFriend(userId, friendId);
        return ResponseEntity.ok("Friend removed successfully");
    }

    // 取得使用者的追蹤者清單
    @GetMapping("/{userId}/followers")
    public ResponseEntity<List<UserDto>> getFollowers(@PathVariable("userId") int userId) {
        List<UserDto> followers = userService.getFollowers(userId);
        return ResponseEntity.ok(followers);
    }

    // 取得使用者正在追蹤的人清單
    @GetMapping("/{userId}/following")
    public ResponseEntity<List<UserDto>> getFollowing(@PathVariable("userId") int userId) {
        List<UserDto> following = userService.getFollowing(userId);
        return ResponseEntity.ok(following);
    }

    // 追蹤使用者
    @PostMapping("/{userId}/follow")
    public ResponseEntity<String> followUser(@PathVariable("userId") int userId,
            @RequestBody Map<String, Integer> request) {
        int followUserId = request.get("followUserId");
        userService.followUser(userId, followUserId);
        return ResponseEntity.ok("User followed successfully");
    }

    // 取消追蹤使用者
    @PostMapping("/{userId}/unfollow")
    public ResponseEntity<String> unfollowUser(@PathVariable("userId") int userId,
            @RequestBody Map<String, Integer> request) {
        int unfollowUserId = request.get("unfollowUserId");
        userService.unfollowUser(userId, unfollowUserId);
        return ResponseEntity.ok("User unfollowed successfully");
    }
}
