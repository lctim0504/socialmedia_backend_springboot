package com.example.demo.dto;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PostDto {
    @NotNull
    private Integer id;

    @NotNull
    private Integer authorId;

    private UserDto author;

    @NotBlank(message = "Content is required")
    private String content;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private Integer commentQty; // 評論數
    private CommentDto comment; // 取讚數最多的comment顯示
    private List<CommentDto> comments; // 取讚數最多的comment顯示

    private List<UserDto> likes;
    private List<UserDto> shares;
    private List<TagDto> tags;

    // getters 和 setters

    public UserDto getAuthor() {
        return this.author;
    }

    public void setAuthor(UserDto author) {
        this.author = author;
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

    public Integer getCommentQty() {
        return this.commentQty;
    }

    public void setCommentQty(Integer commentQty) {
        this.commentQty = commentQty;
    }

    public CommentDto getComment() {
        return this.comment;
    }

    public void setComment(CommentDto comment) {
        this.comment = comment;
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
