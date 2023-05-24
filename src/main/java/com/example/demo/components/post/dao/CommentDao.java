package com.example.demo.components.post.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Comment;

public interface CommentDao extends JpaRepository<Comment, Integer>{

}
