package com.example.demo.user;

import org.springframework.stereotype.Service;

import com.example.demo.dto.UserDto;
import com.example.demo.enums.FriendStatus;
import com.example.demo.model.User;
import com.example.demo.model.UserFollower;
import com.example.demo.model.UserFriend;
import com.example.demo.user.dao.UserDao;
import com.example.demo.user.dao.UserFollowerDao;
import com.example.demo.user.dao.UserFriendDao;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserDao userRepository;
    private final UserFriendDao userFriendRepository;
    private final UserFollowerDao userFollowerRepository;

    public UserService(UserDao userRepository, UserFriendDao userFriendRepository,
            UserFollowerDao userFollowerRepository) {
        this.userRepository = userRepository;
        this.userFriendRepository = userFriendRepository;
        this.userFollowerRepository = userFollowerRepository;
    }

    /* 用戶相關 --------------------------------------- */
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return mapUsersToDto(users);
    }

    public UserDto getUserById(int id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.map(this::mapUserToDto).orElse(null);
    }

    public UserDto createUser(UserDto userDto) {
        User user = mapDtoToUser(userDto);
        User createdUser = userRepository.save(user);
        return mapUserToDto(createdUser);
    }

    public UserDto updateUser(int id, UserDto userDto) {
        Optional<User> optionalUser = userRepository.findById(id);
        optionalUser.ifPresent(user -> {
            user.setUsername(userDto.getUsername());
            user.setEmail(userDto.getEmail());
            user.setPassword(userDto.getPassword());
            userRepository.save(user);
        });
        return optionalUser.map(this::mapUserToDto).orElse(null);
    }

    public boolean deleteUser(int id) {
        Optional<User> optionalUser = userRepository.findById(id);
        optionalUser.ifPresent(user -> userRepository.delete(user));
        return optionalUser.isPresent();
    }

    /* 好友相關 --------------------------------------- */
    public List<UserDto> getFriends(int userId) {
        List<UserFriend> userFriends = userFriendRepository.findByUserIdOrFriendIdAndStatus(userId,
                FriendStatus.ACCEPTED);
        return userFriends.stream()
                .map(userFriend -> {
                    UserDto userDto = new UserDto();
                    userDto.setId(userFriend.getFriend().getId());
                    userDto.setUsername(userFriend.getFriend().getUsername());
                    return userDto;
                })
                .collect(Collectors.toList());
    }

    public void addFriend(int userId, int friendId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        Optional<User> optionalFriend = userRepository.findById(friendId);

        if (optionalUser.isPresent() && optionalFriend.isPresent()) {
            User user = optionalUser.get();
            User friend = optionalFriend.get();
            UserFriend userFriend = new UserFriend();
            userFriend.setUser(user);
            userFriend.setFriend(friend);
            userFriend.setStatus(FriendStatus.PENDING);
            userFriendRepository.save(userFriend);
        }
    }

    public void removeFriend(int userId, int friendId) {
        Optional<UserFriend> optionalUserFriend = userFriendRepository.findByUserIdAndFriendId(userId, friendId);
        optionalUserFriend.ifPresent(userFriendRepository::delete);
    }

    /* 追蹤相關 --------------------------------------- */
    public List<UserDto> getFollowers(int userId) {
        List<UserFollower> followers = userFollowerRepository.findByUserId(userId);
        return followers.stream()
                .map(userFollower -> {
                    UserDto userDto = new UserDto();
                    userDto.setId(userFollower.getFollower().getId());
                    userDto.setUsername(userFollower.getFollower().getUsername());
                    return userDto;
                })
                .collect(Collectors.toList());
    }

    public List<UserDto> getFollowing(int userId) {
        List<UserFollower> following = userFollowerRepository.findByFollowerId(userId);
        return following.stream()
                .map(userFriend -> {
                    UserDto userDto = new UserDto();
                    userDto.setId(userFriend.getUser().getId());
                    userDto.setUsername(userFriend.getUser().getUsername());
                    return userDto;
                })
                .collect(Collectors.toList());
    }

    public void followUser(int userId, int followUserId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        Optional<User> optionalFollowUser = userRepository.findById(followUserId);
        if (optionalUser.isPresent() && optionalFollowUser.isPresent()) {
            User user = optionalUser.get();
            User followUser = optionalFollowUser.get();
            UserFollower userFollower = new UserFollower();
            userFollower.setUser(user);
            userFollower.setFollower(followUser);
            userFollowerRepository.save(userFollower);
        }
    }

    public void unfollowUser(int userId, int unfollowUserId) {
        Optional<UserFollower> optionalUserFollower = userFollowerRepository.findByUserIdAndFollowerId(userId,
                unfollowUserId);
        optionalUserFollower.ifPresent(userFollowerRepository::delete);
    }

    /* Map映射 --------------------------------------- */
    private UserDto mapUserToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        return userDto;
    }

    private List<UserDto> mapUsersToDto(List<User> users) {
        return users.stream().map(this::mapUserToDto).collect(Collectors.toList());
    }

    private User mapDtoToUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        return user;
    }
}
