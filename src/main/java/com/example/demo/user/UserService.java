package com.example.demo.user;

import org.springframework.stereotype.Service;

import com.example.demo.dto.UserDto;
import com.example.demo.model.User;
import com.example.demo.model.UserFollower;
import com.example.demo.model.UserFriend;
import com.example.demo.user.repos.UserFollowerRepository;
import com.example.demo.user.repos.UserFriendRepository;
import com.example.demo.user.repos.UserRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserFriendRepository userFriendRepository;
    private final UserFollowerRepository userFollowerRepository;

    public UserService(UserRepository userRepository, UserFriendRepository userFriendRepository,
            UserFollowerRepository userFollowerRepository) {
        this.userRepository = userRepository;
        this.userFriendRepository = userFriendRepository;
        this.userFollowerRepository = userFollowerRepository;
    }

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
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setUsername(userDto.getUsername());
            user.setEmail(userDto.getEmail());
            user.setPassword(userDto.getPassword());
            User updatedUser = userRepository.save(user);
            return mapUserToDto(updatedUser);
        }
        return null;
    }

    public boolean deleteUser(int id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            userRepository.delete(optionalUser.get());
            return true;
        }
        return false;
    }

    public List<UserDto> getFriends(int userId) {
        List<UserFriend> friends = userFriendRepository.findByUserId(userId);
        return friends.stream()
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
            userFriendRepository.save(userFriend);
        }
    }

    public void removeFriend(int userId, int friendId) {
        Optional<UserFriend> optionalUserFriend = userFriendRepository.findByUserIdAndFriendId(userId, friendId);
        optionalUserFriend.ifPresent(userFriendRepository::delete);
    }

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
