package com.example.demo.components.user;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.Mapping;
import com.example.demo.dto.UserDto;
import com.example.demo.model.User;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserDao userRepository;
    private final Mapping mapping;

    public UserService(
            Mapping mapping,
            UserDao userRepository) {
        this.mapping = mapping;
        this.userRepository = userRepository;
    }

    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return mapping.UsersToDto(users);
    }

    public UserDto getUserById(int userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            return mapping.UserToDto(optionalUser.get());
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with ID: " + userId);
    }

    public UserDto createUser(UserDto userDto) {
        User user = mapping.DtoToUser(userDto);
        User createdUser = userRepository.save(user);
        return mapping.UserToDto(createdUser);
    }

    public UserDto updateUser(int userId, UserDto userDto) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setUsername(userDto.getUsername());
            user.setEmail(userDto.getEmail());
            user.setPassword(userDto.getPassword());
            userRepository.save(user);
            return mapping.UserToDto(user);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with ID: " + userId);
    }

    public void deleteUser(int userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            userRepository.delete(optionalUser.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with ID: " + userId);
        }
    }
}
