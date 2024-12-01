package com.swengineer.sportsmatch.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "Team")
public class TeamEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private int teamId; // 팀 ID (Primary Key)

    @Column(name = "team_name", nullable = false, length = 100)
    private String teamName; // 팀 이름

    @ManyToOne
    @JoinColumn(name = "team_leader_id", nullable = false) // 컬럼 이름 확인
    private UserEntity leader; // 팀 리더 (Foreign Key)


    @OneToOne(mappedBy = "homeTeam", cascade = CascadeType.ALL, orphanRemoval = true)
    private MatchEntity homeMatch; // 홈 팀으로 설정된 매치

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TeamMemberEntity> teamMembers = new ArrayList<>();

    public Collection<UserEntity> getTeamMembers() {
        // TeamMemberEntity에서 UserEntity를 추출하여 반환
        return teamMembers.stream()
                .map(TeamMemberEntity::getUser)
                .toList();
    }
}