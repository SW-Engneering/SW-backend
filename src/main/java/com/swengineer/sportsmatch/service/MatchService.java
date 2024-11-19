package com.swengineer.sportsmatch.service;

import com.swengineer.sportsmatch.dto.MatchDTO;
import com.swengineer.sportsmatch.entity.Match;
import com.swengineer.sportsmatch.entity.Team;
import com.swengineer.sportsmatch.exception.ResourceNotFoundException;
import com.swengineer.sportsmatch.repository.MatchRepository;
import com.swengineer.sportsmatch.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MatchService {

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private TeamRepository teamRepository;

    public Match createMatch(MatchDTO matchDTO) {
        Match match = new Match();
        match.setHomeTeamId(matchDTO.getHomeTeamId());
        match.setAwayTeamId(matchDTO.getAwayTeamId());
        match.setMatchDate(matchDTO.getMatchDate());
        match.setLocation(matchDTO.getLocation());
        match.setStatus("예정");
        return matchRepository.save(match);
    }

    public Match updateMatch(Integer matchId, MatchDTO matchDTO) {
        Match match = getMatchById(matchId);
        match.setMatchDate(matchDTO.getMatchDate());
        match.setLocation(matchDTO.getLocation());
        match.setStatus(matchDTO.getStatus());
        return matchRepository.save(match);
    }

    public void deleteMatch(Integer matchId) {
        matchRepository.deleteById(matchId);
    }

    public Match getMatchById(Integer matchId) {
        return matchRepository.findById(matchId)
                .orElseThrow(() -> new ResourceNotFoundException("매칭을 찾을 수 없습니다."));
    }

    public List<MatchDTO> getAllMatches() {
        List<Match> matches = matchRepository.findAll();
        return matches.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public MatchDTO getMatchDTOById(Integer matchId) {
        Match match = getMatchById(matchId);
        return convertToDTO(match);
    }

    private MatchDTO convertToDTO(Match match) {
        MatchDTO dto = new MatchDTO();
        dto.setMatchId(match.getMatchId());
        dto.setHomeTeamId(match.getHomeTeamId());
        dto.setAwayTeamId(match.getAwayTeamId());
        dto.setMatchDate(match.getMatchDate());
        dto.setLocation(match.getLocation());
        dto.setStatus(match.getStatus());

        Team homeTeam = teamRepository.findById(match.getHomeTeamId())
                .orElseThrow(() -> new ResourceNotFoundException("홈 팀을 찾을 수 없습니다."));
        dto.setHomeTeamName(homeTeam.getTeamName());

        Team awayTeam = teamRepository.findById(match.getAwayTeamId())
                .orElseThrow(() -> new ResourceNotFoundException("원정 팀을 찾을 수 없습니다."));
        dto.setAwayTeamName(awayTeam.getTeamName());

        return dto;
    }
}
