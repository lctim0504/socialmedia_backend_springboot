package com.example.demo.user.friend;

import org.springframework.stereotype.Service;

import com.example.demo.dto.UserDto;
import com.example.demo.enums.FriendStatus;
import com.example.demo.model.User;
import com.example.demo.model.UserFriend;
import com.example.demo.user.UserDao;

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
        List<UserFriend> userFriends = userFriendDao.findByUserIdOrFriendIdAndStatus(userId,
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
        Optional<User> optionalUser = userDao.findById(userId);
        Optional<User> optionalFriend = userDao.findById(friendId);

        if (optionalUser.isPresent() && optionalFriend.isPresent()) {
            User user = optionalUser.get();
            User friend = optionalFriend.get();
            UserFriend userFriend = new UserFriend();
            userFriend.setUser(user);
            userFriend.setFriend(friend);
            userFriend.setStatus(FriendStatus.PENDING);
            userFriendDao.save(userFriend);
        }
    }

    public void removeFriend(int userId, int friendId) {
        Optional<UserFriend> optionalUserFriend = userFriendDao.findByUserIdAndFriendId(userId, friendId);
        optionalUserFriend.ifPresent(userFriendDao::delete);
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
