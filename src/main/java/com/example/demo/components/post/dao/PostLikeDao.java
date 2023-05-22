package com.example.demo.components.post.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.PostLikes;

@Repository
public interface PostLikeDao extends JpaRepository<PostLikes, Integer> {

    Optional<PostLikes> findByUserIdAndPostId(Integer userId, Integer postId);

    void deleteByUserIdAndPostId(int userId, int postId);

    boolean existsByUserIdAndPostId(int userId, int postId);
}
