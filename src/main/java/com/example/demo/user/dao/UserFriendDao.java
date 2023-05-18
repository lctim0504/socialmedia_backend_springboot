package com.example.demo.user.repos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.UserFriend;

@Repository
public interface UserFriendRepository extends JpaRepository<UserFriend, Integer> {

    Optional<UserFriend> findByUserIdAndFriendId(Integer userId, Integer friendId);
    List<UserFriend> findByUserId(Integer userId);
}
