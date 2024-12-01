package com.sparta.sw_backend_team.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@IdClass(TeamMemberId.class) // 복합 키 설정
public class TeamMember {

    @Id
    @Column(nullable = false) // team_id, NOT NULL
    private int teamId;

    @Id
    @Column(nullable = false) // user_id, NOT NULL
    private int userId;

    @Column(nullable = true, columnDefinition = "ENUM('leader', 'member') DEFAULT 'member'") // role
    private String role;

    @Column(nullable = true) // joined_at, CURRENT_TIMESTAMP DEFAULT GENERATED
    private LocalDateTime joinedAt;

    // 기본 생성자
    public TeamMember() {
    }

    // 모든 필드를 포함한 생성자
    public TeamMember(int teamId, int userId, String role, LocalDateTime joinedAt) {
        this.teamId = teamId;
        this.userId = userId;
        this.role = role;
        this.joinedAt = joinedAt;
    }

    // Getter와 Setter
    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public LocalDateTime getJoinedAt() {
        return joinedAt;
    }

    public void setJoinedAt(LocalDateTime joinedAt) {
        this.joinedAt = joinedAt;
    }
}

