package com.example.demo.user.repos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.UserFollower;

@Repository
public interface UserFollowerRepository extends JpaRepository<UserFollower, Integer> {
    
    Optional<UserFollower> findByUserIdAndFollowerId(Integer userId, Integer followUserId);
    List<UserFollower> findByUserId(Integer userId);
    List<UserFollower> findByFollowerId(Integer followUserId);

}
