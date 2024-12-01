package com.sparta.sw_backend_team.dto;

import lombok.Data;

@Data
public class UserDto {
    private int userId;
    private String name;
    private String nickname;
    private String position;
    private Integer age;
    private String gender;
}
