package com.swengineer.sportsmatch.service;

import com.swengineer.sportsmatch.dto.MatchDTO;
import com.swengineer.sportsmatch.entity.MatchEntity;
import com.swengineer.sportsmatch.entity.TeamEntity;
import com.swengineer.sportsmatch.repository.MatchRepository;
import com.swengineer.sportsmatch.repository.TeamRepository;
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
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Home team not found"));

        TeamEntity awayTeam = teamRepository.findById(awayTeamId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Away team not found"));

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
}
