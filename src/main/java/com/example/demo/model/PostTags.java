package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "post_tags")
@IdClass(PostTagsId.class)
public class PostTags {

    @Id
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;
    
    @Id
    @ManyToOne
    @JoinColumn(name = "tag_id")
    private Tag tag;

    // 省略其他属性和方法

    // Getter 和 Setter 方法

    public Post getPost() {
        return this.post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Tag getTag() {
        return this.tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

}