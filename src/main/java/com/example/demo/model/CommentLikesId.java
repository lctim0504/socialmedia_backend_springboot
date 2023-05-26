package com.example.demo.model;
import java.io.Serializable;

public class CommentLikesId implements Serializable {

    private int comment;
    private int user;

    public int getComment() {
        return this.comment;
    }

    public void setComment(int comment) {
        this.comment = comment;
    }

    public int getUser() {
        return this.user;
    }

    public void setUser(int user) {
        this.user = user;
    }

}
