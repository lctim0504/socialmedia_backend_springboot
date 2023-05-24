package com.example.demo.components.post.search;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Post;

public interface PostSearchDao extends JpaRepository<Post, Integer> {

    List<Post> findByContentContaining(String contentQuery);
}