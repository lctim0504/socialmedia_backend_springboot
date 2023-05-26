package com.example.demo.components.user.friend;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.components.user.UserDao;
import com.example.demo.dto.UserDto;
import com.example.demo.enums.FriendStatus;
import com.example.demo.model.User;
import com.example.demo.model.UserFriend;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserFriendService {

    private final UserDao userDao;
    private final UserFriendDao userFriendDao;

    public UserFriendService(UserDao userDao, UserFriendDao userFriendDao) {
        this.userDao = userDao;
        this.userFriendDao = userFriendDao;
    }

    /* 好友相關 --------------------------------------- */
    public List<UserDto> getFriends(int userId) {
        List<UserFriend> userFriends = userFriendDao.findByUserIdAndStatus(userId,
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
        if (userId == friendId)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User and Friend cannot be the same entity");

        Optional<User> optionalUser = userDao.findById(userId);
        Optional<User> optionalFriend = userDao.findById(friendId);

        if (optionalUser.isPresent() && optionalFriend.isPresent()) {
            // 取得目前關係
            Optional<UserFriend> myCurrentStatus = userFriendDao.getCurrentStatus(userId, friendId);
            Optional<UserFriend> herCurrentStatus = userFriendDao.getCurrentStatus(friendId, userId);

            UserFriend myStatus = new UserFriend();
            myStatus.setUser(optionalUser.get());
            myStatus.setFriend(optionalFriend.get());
            // 對方的狀態
            UserFriend herStatus = new UserFriend();
            herStatus.setUser(optionalFriend.get());
            herStatus.setFriend(optionalUser.get());

            // 沒紀錄 => 新增紀錄
            if (!myCurrentStatus.isPresent() && !herCurrentStatus.isPresent()) {
                myStatus.setStatus(FriendStatus.PENDING);
                userFriendDao.save(myStatus);
            }
            // 有紀錄
            if (herCurrentStatus.isPresent()) {
                if (herCurrentStatus.get().getStatus() != FriendStatus.REJECTED) {
                    myStatus.setStatus(FriendStatus.ACCEPTED);
                    userFriendDao.save(myStatus);
                    herStatus.setStatus(FriendStatus.ACCEPTED);
                    userFriendDao.save(herStatus);
                }
            }
            if (myCurrentStatus.isPresent()) {
                if (myCurrentStatus.get().getStatus() != FriendStatus.ACCEPTED) {
                    myStatus.setStatus(FriendStatus.PENDING);
                    userFriendDao.save(myStatus);
                }
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User or Friend not found");
        }
    }

    public void removeFriend(int userId, int friendId) {
        if (userId == friendId)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User and Friend cannot be the same entity");
        Optional<User> optionalUser = userDao.findById(userId);
        Optional<User> optionalFriend = userDao.findById(friendId);

        if (optionalUser.isPresent() && optionalFriend.isPresent()) {
            // 取得目前關係
            Optional<UserFriend> myCurrentStatus = userFriendDao.getCurrentStatus(userId, friendId);
            Optional<UserFriend> herCurrentStatus = userFriendDao.getCurrentStatus(friendId, userId);

            if (myCurrentStatus.isPresent() && herCurrentStatus.isPresent()) {
                userFriendDao.delete(myCurrentStatus.get());

                UserFriend herStatus = herCurrentStatus.get();
                herStatus.setStatus(FriendStatus.PENDING);
                userFriendDao.save(herStatus);
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "UserFriend not found");
        }
    }
}
