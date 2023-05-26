package com.example.demo.components.comment.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.CommentLikes;

public interface CommentLikeDao extends JpaRepository<CommentLikes, Integer> {
    Optional<CommentLikes> findByCommentIdAndUserId(int commentId, int userId);
}