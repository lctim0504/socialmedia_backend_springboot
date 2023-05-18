package com.example.demo.dto;

import jakarta.validation.constraints.NotEmpty;

public class UserLikeDto {

    @NotEmpty
    private int userId;

}
