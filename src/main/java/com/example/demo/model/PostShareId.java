package com.example.demo.model;

import java.io.Serializable;

public class PostShareId implements Serializable {
    private Post post;
    private User user;

    public Post getPost() {
        return this.post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
