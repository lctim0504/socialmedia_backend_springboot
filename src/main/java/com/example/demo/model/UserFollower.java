package com.example.demo.model;
import jakarta.persistence.*;

@Entity
@Table(name = "user_followers")
@IdClass(UserFollowerId.class)
public class UserFollower {

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "follower_id")
    private User follower;

    // Constructors, getters, setters, and other methods

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getFollower() {
        return this.follower;
    }

    public void setFollower(User follower) {
        this.follower = follower;
    }
}

