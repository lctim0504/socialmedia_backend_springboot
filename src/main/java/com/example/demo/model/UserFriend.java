package com.example.demo.model;

import com.example.demo.enums.FriendStatus;

import jakarta.persistence.*;

@Entity
@Table(name = "user_friends")
@IdClass(UserFriendId.class)
public class UserFriend {

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "friend_id")
    private User friend;

    @Enumerated(EnumType.STRING)
    private FriendStatus status;

    // Constructors, getters, setters, and other methods

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getFriend() {
        return this.friend;
    }

    public void setFriend(User friend) {
        this.friend = friend;
    }

    public FriendStatus getStatus() {
        return this.status;
    }

    public void setStatus(FriendStatus status) {
        this.status = status;
    }
}
