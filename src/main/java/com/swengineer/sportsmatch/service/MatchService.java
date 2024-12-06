package com.swengineer.sportsmatch.service;

import com.swengineer.sportsmatch.dto.MatchDTO;
import com.swengineer.sportsmatch.entity.MatchEntity;
import com.swengineer.sportsmatch.entity.TeamEntity;
import com.swengineer.sportsmatch.repository.MatchRepository;
import com.swengineer.sportsmatch.repository.TeamRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MatchService {

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private TeamRepository teamRepository;

    public MatchDTO createMatch(int homeTeamId, int awayTeamId, MatchDTO matchDTO) {
        TeamEntity homeTeam = teamRepository.findById(homeTeamId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "홈팀을 찾을 수 없습니다."));

        TeamEntity awayTeam = teamRepository.findById(awayTeamId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "어웨이팀을 찾을 수 없습니다."));

        // 이미 매칭 중인 팀인지 확인
        if (isTeamInMatch(homeTeam)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "홈팀은 이미 매칭 중입니다.");
        }

        if (isTeamInMatch(awayTeam)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "어웨이팀은 이미 매칭 중입니다.");
        }

        // 매칭 생성
        MatchEntity matchEntity = MatchEntity.builder()
                .homeTeam(homeTeam)
                .awayTeam(awayTeam)
                .matchDate(matchDTO.getMatchDate())
                .location(matchDTO.getLocation())
                .createdAt(LocalDateTime.now())
                .build();

        MatchEntity savedMatch = matchRepository.save(matchEntity);
        return MatchDTO.toMatchDTO(savedMatch);
    }

    // 매칭 중인지 확인하는 메서드
    private boolean isTeamInMatch(TeamEntity team) {
        // 매칭이 있는지 확인하는 쿼리, homeTeam 또는 awayTeam으로 참여 중인 매칭이 있는지 확인
        return matchRepository.existsByHomeTeamOrAwayTeam(team, team);
    }



    public MatchDTO getMatchById(int matchId) {
        MatchEntity matchEntity = matchRepository.findById(matchId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Match not found"));
        return MatchDTO.toMatchDTO(matchEntity);
    }

    public List<MatchDTO> getAllMatches() {
        return matchRepository.findAll().stream()
                .map(MatchDTO::toMatchDTO)
                .collect(Collectors.toList());
    }

    public void deleteMatch(int matchId) {
        if (!matchRepository.existsById(matchId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Match not found");
        }
        matchRepository.deleteById(matchId);
    }

    public List<MatchDTO> getMatchesByTeamId(int teamId) {
        List<MatchEntity> matches = matchRepository.findByHomeTeam_TeamIdOrAwayTeam_TeamId(teamId, teamId);

        if (matches.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No matches found for the given team ID");
        }

        return matches.stream()
                .map(MatchDTO::toMatchDTO)
                .collect(Collectors.toList());
    }

    public MatchDTO updateMatch(int matchId, MatchDTO matchDTO) {
        MatchEntity match = matchRepository.findById(matchId)
                .orElseThrow(() -> new EntityNotFoundException("Match not found with ID: " + matchId));

        if (matchDTO.getMatchDate() != null) {
            match.setMatchDate(matchDTO.getMatchDate());
        }
        if (matchDTO.getLocation() != null) {
            match.setLocation(matchDTO.getLocation());
        }
        MatchEntity updatedMatch = matchRepository.save(match);
        return MatchDTO.toMatchDTO(updatedMatch);
    }


}
