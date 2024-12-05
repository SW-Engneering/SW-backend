package com.swengineer.sportsmatch.dto;

import com.swengineer.sportsmatch.entity.MatchEntity;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MatchDTO {
    private int matchId;
    private int homeTeamId;
    private int awayTeamId;
    private LocalDate matchDate;
    private String location;

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