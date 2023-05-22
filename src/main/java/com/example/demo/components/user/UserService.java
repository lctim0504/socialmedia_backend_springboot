package com.example.demo.components.user;

import org.springframework.stereotype.Service;

import com.example.demo.dto.UserDto;
import com.example.demo.model.User;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserDao userRepository;

    public UserService(UserDao userRepository) {
        this.userRepository = userRepository;
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
