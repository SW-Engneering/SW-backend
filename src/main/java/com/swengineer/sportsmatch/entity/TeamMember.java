package com.swengineer.sportsmatch.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "TeamMember")
@IdClass(TeamMember.TeamMemberId.class)
public class TeamMember {

    @Id
    @Column(name = "team_id")
    private Integer teamId;

    @Id
    @Column(name = "user_id")
    private Integer userId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role = Role.MEMBER;

    @Column(nullable = false)
    private LocalDateTime joinedAt = LocalDateTime.now();

    public enum Role {
        LEADER,
        MEMBER
    }

    // 복합 키 클래스
    public static class TeamMemberId implements Serializable {
        private Integer teamId;
        private Integer userId;

        // Constructors, equals, and hashCode methods
    }

    // Getter and Setter methods

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public LocalDateTime getJoinedAt() {
        return joinedAt;
    }

    public void setJoinedAt(LocalDateTime joinedAt) {
        this.joinedAt = joinedAt;
    }
}
