package com.example.demo.dto;

import java.time.LocalDateTime;

public class CommentDto {
    private int id;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private UserDto author;
    private PostDto post;

    // getters 和 setters

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public UserDto getAuthor() {
        return this.author;
    }

    public void setAuthor(UserDto author) {
        this.author = author;
    }

    public PostDto getPost() {
        return this.post;
    }

    public void setPost(PostDto post) {
        this.post = post;
    }

}
