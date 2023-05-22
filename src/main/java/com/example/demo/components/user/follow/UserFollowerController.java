package com.example.demo.components.user.follow;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UserDto;

@RestController
@RequestMapping("/api/users/{userId}")
public class UserFollowerController {

    private final UserFollowerService userService;

    public UserFollowerController(UserFollowerService userService) {
        this.userService = userService;
    }

    // 取得使用者的追蹤者清單
    @GetMapping("/followers")
    public ResponseEntity<List<UserDto>> getFollowers(@PathVariable("userId") int userId) {
        List<UserDto> followers = userService.getFollowers(userId);
        return ResponseEntity.ok(followers);
    }

    // 取得使用者正在追蹤的人清單
    @GetMapping("/following")
    public ResponseEntity<List<UserDto>> getFollowing(@PathVariable("userId") int userId) {
        List<UserDto> following = userService.getFollowing(userId);
        return ResponseEntity.ok(following);
    }

    // 追蹤使用者
    @PostMapping("/follow")
    public ResponseEntity<String> followUser(@PathVariable("userId") int userId,
            @RequestBody Map<String, Integer> request) {
        int followUserId = request.get("followUserId");
        userService.followUser(userId, followUserId);
        return ResponseEntity.ok("User followed successfully");
    }

    // 取消追蹤使用者
    @PostMapping("/unfollow")
    public ResponseEntity<String> unfollowUser(@PathVariable("userId") int userId,
            @RequestBody Map<String, Integer> request) {
        int unfollowUserId = request.get("unfollowUserId");
        userService.unfollowUser(userId, unfollowUserId);
        return ResponseEntity.ok("User unfollowed successfully");
    }
}
