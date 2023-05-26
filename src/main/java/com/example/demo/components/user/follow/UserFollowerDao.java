package com.example.demo.components.user.follow;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.UserFollower;

public interface UserFollowerDao extends JpaRepository<UserFollower, Integer> {
    
    Optional<UserFollower> findByUserIdAndFollowerId(Integer userId, Integer followUserId);
    List<UserFollower> findByUserId(Integer userId);
    List<UserFollower> findByFollowerId(Integer followUserId);
}
