package com.example.demo.model;

import java.io.Serializable;

public class UserFriendId implements Serializable {
    private User user;
    private User friend;

    // 建構子、取得器、設定器等方法

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

}
