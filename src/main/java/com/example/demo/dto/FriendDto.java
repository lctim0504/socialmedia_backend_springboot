package com.example.demo.dto;

public class FriendDto {

    private int id;
    private int userId;
    private int friendId;

    /* ---------------- Getter and setter ----------------- */

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getFriendId() {
        return this.friendId;
    }

    public void setFriendId(int friendId) {
        this.friendId = friendId;
    }

}
