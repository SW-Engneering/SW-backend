package com.sparta.sw_backend_team.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class TeamMatch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int matchId; // Primary Key, auto_increment

    @Column(nullable = false) // team_id, NOT NULL
    private int teamId;

    @Column(nullable = false) // match_date, NOT NULL
    private LocalDate matchDate;

    @Column(nullable = false, length = 255) // location, VARCHAR(255), NOT NULL
    private String location;

    // 기본 생성자
    public TeamMatch() {
    }

    // 모든 필드를 포함한 생성자
    public TeamMatch(int teamId, LocalDate matchDate, String location) {
        this.teamId = teamId;
        this.matchDate = matchDate;
        this.location = location;
    }

    // Getter와 Setter
    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public LocalDate getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(LocalDate matchDate) {
        this.matchDate = matchDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
