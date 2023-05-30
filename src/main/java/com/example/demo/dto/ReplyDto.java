package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;

public class ReplyDto {

    @NotBlank(message = "comment is required")
    private String comment;


    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}