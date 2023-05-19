package com.example.demo.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "user1")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String email;

    @JsonIgnore
    private String password;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // @OneToMany(mappedBy = "author")
    // private List<Post> posts;

    // @OneToMany(mappedBy = "author")
    // private List<Comment> comments;

    // @ManyToMany
    // @JoinTable(name = "user_friends", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "friend_id"))
    // private List<User> friends;

    // @ManyToMany(mappedBy = "friends")
    // private List<User> friendOf;

    // @ManyToMany
    // @JoinTable(name = "user_followers", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "follower_id"))
    // private List<User> followers;

    // @ManyToMany(mappedBy = "followers")
    // private List<User> following;

    // @ManyToMany(mappedBy = "likes")
    // private List<Post> likedPosts;

    // @ManyToMany(mappedBy = "shares")
    // private List<Post> sharedPosts;

    // @OneToOne(mappedBy = "user")
    // private UserSettings settings;

    // @OneToMany(mappedBy = "user")
    // private List<Notification> notifications;

    // Constructors, getters, setters, and other methods

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
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

}
