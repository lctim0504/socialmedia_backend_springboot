package com.example.demo.components.post.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Post;
import java.util.List;

public interface PostDao extends JpaRepository<Post, Integer>{

    List<Post> findByAuthorId(int authorId);
    Page<Post> findByAuthorId(int authorId, Pageable pageable);
}
