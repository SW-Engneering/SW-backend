package com.sparta.sw_backend_team.entity;

import jakarta.persistence.*;

@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int teamId; // Primary Key, auto_increment

    @Column(nullable = false, length = 100) // varchar(100), NOT NULL
    private String teamName;

    @Column(nullable = false) // NOT NULL
    private int userId; // 외래 키로 예상됨

    // 기본 생성자
    public Team() {
    }

    // 모든 필드를 포함한 생성자
    public Team(String teamName, int userId) {
        this.teamName = teamName;
        this.userId = userId;
    }

    // Getter와 Setter
    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
