package com.example.demo.components.post.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.PostLikes;

public interface PostLikeDao extends JpaRepository<PostLikes, Integer> {

    Optional<PostLikes> findByUserIdAndPostId(Integer userId, Integer postId);

    void deleteByUserIdAndPostId(int userId, int postId);

    boolean existsByUserIdAndPostId(int userId, int postId);
}
