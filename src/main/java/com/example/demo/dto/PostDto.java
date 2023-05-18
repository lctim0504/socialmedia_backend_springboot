package com.example.demo.dto;

import java.time.LocalDateTime;
import java.util.List;

public class PostDto {
    private int id;
    private int authorId;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<CommentDto> comments;
    private List<UserDto> likes;
    private List<UserDto> shares;
    private List<TagDto> tags;

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


    public int getAuthorId() {
        return this.authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

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

    public List<UserDto> getShares() {
        return this.shares;
    }

    public void setShares(List<UserDto> shares) {
        this.shares = shares;
    }

    public List<TagDto> getTags() {
        return this.tags;
    }

    public void setTags(List<TagDto> tags) {
        this.tags = tags;
    }

}
