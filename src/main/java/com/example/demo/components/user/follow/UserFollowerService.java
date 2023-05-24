package com.example.demo.components.user.follow;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.components.user.UserDao;
import com.example.demo.dto.UserDto;
import com.example.demo.model.User;
import com.example.demo.model.UserFollower;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserFollowerService {

    private final UserDao userDao;
    private final UserFollowerDao userFollowerDao;

    public UserFollowerService(
            UserDao userDao,
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
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User or Follower not found");
        }
    }

    public void unfollowUser(int userId, int unfollowUserId) {
        Optional<UserFollower> optionalUserFollower = userFollowerDao.findByUserIdAndFollowerId(userId, unfollowUserId);
        if (optionalUserFollower.isPresent()) {
            userFollowerDao.delete(optionalUserFollower.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "UserFollower not found");
        }
    }
}
