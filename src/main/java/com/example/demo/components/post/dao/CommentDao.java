package com.example.demo.components.post.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Comment;

public interface CommentDao extends JpaRepository<Comment, Integer> {

    List<Comment> findByPostId(int postId);
    List<Comment> findByParentId(int parentId);
    Page<Comment> findByPostId(int postId, Pageable pageable);
}
