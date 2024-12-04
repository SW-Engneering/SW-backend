package com.swengineer.sportsmatch.entity;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "teammatch")
public class MatchEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "match_id")
    private int matchId;

    @OneToOne
    @JoinColumn(name = "home_team_id", nullable = false)
    private TeamEntity homeTeam;

    @OneToOne
    @JoinColumn(name = "away_team_id", nullable = false)
    private TeamEntity awayTeam;

    @Column(name = "match_date", nullable = true)
    private LocalDate matchDate;

    @Column(name = "location", nullable = true, length = 255)
    private String location;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDate createdAt;

    @Column(name = "deadline", nullable = false)
    private LocalDate deadline;

    private boolean isCancelled;

    @PostConstruct
    public void init() {
        this.isCancelled = false; // 초기화 값 설정
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDate.now();
        this.deadline = LocalDate.now().plusDays(7); // 기본 기한 설정
    }
}