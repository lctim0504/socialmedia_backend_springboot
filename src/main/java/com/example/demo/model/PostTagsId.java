package com.example.demo.model;

import java.io.Serializable;

public class PostTagsId implements Serializable {

    private int post;
    private int tag;

    public int getPost() {
        return this.post;
    }

    public void setPost(int post) {
        this.post = post;
    }

    public int getTag() {
        return this.tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

}
