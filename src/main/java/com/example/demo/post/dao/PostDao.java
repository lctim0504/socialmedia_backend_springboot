package com.example.demo.post.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Post;
import java.util.List;

@Repository
public interface PostDao extends JpaRepository<Post, Integer>{

    List<Post> findByAuthorId(int authorId);
}
