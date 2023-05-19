package com.example.demo.user.follow;

import org.springframework.stereotype.Service;

import com.example.demo.dto.UserDto;
import com.example.demo.model.User;
import com.example.demo.model.UserFollower;
import com.example.demo.user.UserDao;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserFollowerService {

    private final UserDao userDao;
    private final UserFollowerDao userFollowerDao;

    public UserFollowerService(UserDao userDao,
            UserFollowerDao userFollowerDao) {
        this.userDao = userDao;
        this.userFollowerDao = userFollowerDao;
    }

    public List<UserDto> getFollowers(int userId) {
        List<UserFollower> followers = userFollowerDao.findByUserId(userId);
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
        List<UserFollower> following = userFollowerDao.findByFollowerId(userId);
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
        Optional<User> optionalUser = userDao.findById(userId);
        Optional<User> optionalFollowUser = userDao.findById(followUserId);
        if (optionalUser.isPresent() && optionalFollowUser.isPresent()) {
            User user = optionalUser.get();
            User followUser = optionalFollowUser.get();
            UserFollower userFollower = new UserFollower();
            userFollower.setUser(user);
            userFollower.setFollower(followUser);
            userFollowerDao.save(userFollower);
        }
    }

    public void unfollowUser(int userId, int unfollowUserId) {
        Optional<UserFollower> optionalUserFollower = userFollowerDao.findByUserIdAndFollowerId(userId,
                unfollowUserId);
        optionalUserFollower.ifPresent(userFollowerDao::delete);
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
