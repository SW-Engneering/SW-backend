package com.swengineer.sportsmatch.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "team")
public class TeamEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private int teamId; // 팀 ID (Primary Key)

    @Column(name = "team_name", nullable = false, length = 100)
    private String teamName; // 팀 이름

    @Column(name = "team_region", nullable = false, length = 100)
    private String teamRegion; // 팀 지역

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "team_leader_id", nullable = false) // 팀 리더와의 관계 설정
    private UserEntity leader; // 팀 리더 (Foreign Key)

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<TeamMemberEntity> teamMembers = new ArrayList<>(); // 팀 멤버 리스트

    @OneToOne(mappedBy = "homeTeam", cascade = CascadeType.ALL, orphanRemoval = true)
    private MatchEntity homeMatch;  // 홈 팀으로 설정된 매치 (하나의 매치만)

    @OneToOne(mappedBy = "awayTeam", cascade = CascadeType.ALL, orphanRemoval = true)
    private MatchEntity awayMatch;  // 원정 팀으로 설정된 매치 (하나의 매치만)

    // 팀 멤버를 UserEntity의 리스트로 반환
    public List<UserEntity> getUserEntities() {
        return teamMembers.stream()
                .map(TeamMemberEntity::getUser)
                .collect(Collectors.toList());
    }

    // JSON 직렬화를 위한 생성자
    @JsonCreator
    public TeamEntity(
            @JsonProperty("teamId") int teamId,
            @JsonProperty("teamName") String teamName,
            @JsonProperty("teamRegion") String teamRegion) {
        this.teamId = teamId;
        this.teamName = teamName;
        this.teamRegion = teamRegion;
    }
}

