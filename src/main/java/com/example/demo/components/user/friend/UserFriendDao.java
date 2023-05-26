package com.example.demo.components.user.friend;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.enums.FriendStatus;
import com.example.demo.model.UserFriend;

public interface UserFriendDao extends JpaRepository<UserFriend, Integer> {

    // List<UserFriend> findByUserIdOrFriendId(Integer userId, Integer friendId);

    // 取得單一方的好友關係
    @Query("SELECT uf FROM UserFriend uf WHERE uf.user.id = :userId AND uf.status = :status")
    List<UserFriend> findByUserIdAndStatus(Integer userId, FriendStatus status);

    // 取得雙方好友關係
    @Query("SELECT uf FROM UserFriend uf WHERE uf.user.id = :userId AND uf.friend.id = :friendId")
    Optional<UserFriend> getCurrentStatus(Integer userId, Integer friendId);

    List<UserFriend> findByUserId(Integer userId);
}
