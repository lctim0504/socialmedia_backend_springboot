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

import java.util.List;
import java.util.Optional;
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

    /*
     * optionalUser.get() 是用於從 Optional 對象中取得實際的 User
     * 物件。Optional 是一個容器類型，用於處理可能為空（null）的值。透過 optionalUser.get() 方法，
     * 我們可以從 Optional 對象中提取實際的非空值，以便在後續程式碼中使用。
     * 
     * Optional<User> 對象並不是 User 類型的實例，而是一個包裹了可能為空的 User 物件的容器。
     * 因此我們需要透過 optionalUser.get() 方法來取得實際的 User 物件，然後再將其設定到 UserFriend 對象中。
     */
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
