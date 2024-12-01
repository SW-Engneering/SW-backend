package com.sparta.sw_backend_team.entity;

import java.io.Serializable;
import java.util.Objects;

public class TeamMemberId implements Serializable {
    private int teamId;
    private int userId;

    // 기본 생성자
    public TeamMemberId() {
    }

    // 모든 필드를 포함한 생성자
    public TeamMemberId(int teamId, int userId) {
        this.teamId = teamId;
        this.userId = userId;
    }

    // equals와 hashCode 메서드
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeamMemberId that = (TeamMemberId) o;
        return teamId == that.teamId && userId == that.userId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(teamId, userId);
    }
}

