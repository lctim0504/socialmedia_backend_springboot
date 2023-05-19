package com.example.demo.post.search;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Post;

@Repository
public interface PostSearchDao extends JpaRepository<Post, Integer> {

    List<Post> findByContentContaining(String contentQuery);
}