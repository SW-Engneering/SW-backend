package com.swengineer.sportsmatch.dto;

import com.swengineer.sportsmatch.entity.CommentEntity;
import com.swengineer.sportsmatch.entity.MatchEntity;

import com.swengineer.sportsmatch.entity.TeamEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MatchDTO {

    private int matchId; // 매칭 ID (Primary Key)
    private int homeTeamId;
    private int awayTeamId; // 원정 팀 정보 (Foreign Key)
    private LocalDate matchDate; // 매칭 날짜
    private String location; // 매칭 장소/ 매칭 장소

    public static MatchDTO toMatchDTO(MatchEntity matchEntity) {
        MatchDTO matchDTO = new MatchDTO();
        matchDTO.setMatchId(matchEntity.getMatchId());
        matchDTO.setHomeTeamId(matchEntity.getHomeTeam().getTeamId());
        matchDTO.setAwayTeamId(matchEntity.getAwayTeam().getTeamId());
        matchDTO.setMatchDate(matchEntity.getMatchDate());
        matchDTO.setLocation(matchEntity.getLocation());
        return matchDTO;
    }

}