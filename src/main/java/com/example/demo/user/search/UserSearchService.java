package com.example.demo.user.search;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.User;

@Service
public class UserSearchService {

    private final UserSearchDao userDao;

    public UserSearchService(UserSearchDao userDao) {
        this.userDao = userDao;
    }

    public List<User> searchUsers(String query) {
        // Perform the search query on the repository
        return userDao.findByUsernameContaining(query);
    }
}
