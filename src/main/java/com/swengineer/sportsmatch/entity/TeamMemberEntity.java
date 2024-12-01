package com.swengineer.sportsmatch.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "teammember")
public class TeamMemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id; // 단일 기본 키

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) // 외래 키 매핑
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "team_id", nullable = false) // 외래 키 매핑
    private TeamEntity team;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(name = "joined_at", nullable = false, updatable = false)
    private LocalDateTime joinedAt = LocalDateTime.now(); // 가입 날짜

    public enum Role {
        LEADER, MEMBER
    }
}
