package com.example.demo.user.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.enums.FriendStatus;
import com.example.demo.model.UserFriend;

@Repository
public interface UserFriendDao extends JpaRepository<UserFriend, Integer> {

    //List<UserFriend> findByUserIdOrFriendId(Integer userId, Integer friendId);

    @Query("SELECT uf FROM UserFriend uf WHERE (uf.user.id = :userId AND uf.friend.id = :friendId) OR (uf.user.id = :friendId AND uf.friend.id = :userId)")
    Optional<UserFriend> findByUserIdAndFriendId(Integer userId, Integer friendId);

    @Query("SELECT uf FROM UserFriend uf WHERE (uf.user.id = :userId OR uf.friend.id = :userId) AND uf.status = :status")
    List<UserFriend> findByUserIdOrFriendIdAndStatus(Integer userId, FriendStatus status);

    List<UserFriend> findByUserId(Integer userId);
}
