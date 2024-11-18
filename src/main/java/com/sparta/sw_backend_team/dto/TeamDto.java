package com.sparta.sw_backend_team.dto;

import lombok.Data;

import java.util.List;

@Data
public class TeamDto {
    private String teamId;
    private String teamName;
    private String teamRegion;
    private Integer leaderId;
    private List<UserDto> members;
}
