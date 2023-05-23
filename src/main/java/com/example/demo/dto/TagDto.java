package com.example.demo.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TagDto {
    @NotNull
    private Integer id;

    @NotBlank(message = "Name is required")
    private String name;

    private List<PostDto> posts;

    // getters 和 setters

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PostDto> getPosts() {
        return this.posts;
    }

    public void setPosts(List<PostDto> posts) {
        this.posts = posts;
    }

}
