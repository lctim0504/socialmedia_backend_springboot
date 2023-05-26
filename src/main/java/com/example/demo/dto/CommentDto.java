package com.example.demo.dto;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CommentDto {
    @NotNull
    private Integer id;

    @NotBlank(message = "Content is required")
    private String content;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private UserDto author;
    private PostDto post;
    private List<UserDto> likes;
    private List<CommentDto> comments;

    // getters 和 setters

    public List<CommentDto> getComments() {
        return this.comments;
    }

    public void setComments(List<CommentDto> comments) {
        this.comments = comments;
    }

    public List<UserDto> getLikes() {
        return this.likes;
    }

    public void setLikes(List<UserDto> likes) {
        this.likes = likes;
    }

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
