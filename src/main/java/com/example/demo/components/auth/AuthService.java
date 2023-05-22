package com.example.demo.components.auth;

import org.springframework.stereotype.Service;

import com.example.demo.dto.LoginDto;
import com.example.demo.dto.UserDto;
import com.example.demo.model.User;

@Service
public class AuthService {

    private final AuthDao userDao;

    public AuthService(AuthDao userDao) {
        this.userDao = userDao;
    }

    public UserDto signup(UserDto userDto) {

        User newUser = new User();
        newUser.setUsername(userDto.getUsername());
        newUser.setPassword(userDto.getPassword());
        newUser.setEmail(userDto.getEmail());

        User savedUser = userDao.save(newUser);

        return convertToDto(savedUser);
    }

    public UserDto login(LoginDto loginDto) {

        User user = userDao.findByUsernameAndPassword(loginDto.getUsername(), loginDto.getPassword());
        if (user != null) {
            return convertToDto(user);
        } else {
            return null;
        }
    }

    private UserDto convertToDto(User user) {

        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());

        return userDto;
    }
}
