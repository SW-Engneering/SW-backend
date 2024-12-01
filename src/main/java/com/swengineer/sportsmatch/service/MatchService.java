package com.swengineer.sportsmatch.service;

import com.swengineer.sportsmatch.dto.MatchDTO;
import com.swengineer.sportsmatch.entity.MatchEntity;
import com.swengineer.sportsmatch.entity.TeamEntity;
import com.swengineer.sportsmatch.entity.TeamMemberEntity;
import com.swengineer.sportsmatch.repository.MatchRepository;
import com.swengineer.sportsmatch.repository.TeamRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class MatchService {

    private final MatchRepository matchRepository;
    private final TeamRepository teamRepository;

    public MatchDTO createMatch(MatchDTO matchDTO, int homeTeamId, int awayTeamId) {
        TeamEntity homeTeam = teamRepository.findById(homeTeamId)
                .orElseThrow(() -> new EntityNotFoundException("Home team not found with ID: " + homeTeamId));

        TeamEntity awayTeam = teamRepository.findById(awayTeamId)
                .orElseThrow(() -> new EntityNotFoundException("Away team not found with ID: " + awayTeamId));

        if (homeTeam.getTeamMembers().size() < 11 || awayTeam.getTeamMembers().size() < 11) {
            throw new IllegalArgumentException("Both teams must have at least 11 members.");
        }

        if (matchRepository.existsByHomeTeamOrAwayTeam(homeTeam, awayTeam)) {
            throw new IllegalArgumentException("One of the teams is already assigned to another match.");
        }

        MatchEntity match = MatchEntity.builder()
                .homeTeam(homeTeam)
                .awayTeam(awayTeam)
                .matchDate(matchDTO.getMatchDate())
                .location(matchDTO.getLocation())
                .createdAt(LocalDate.now())
                .deadline(LocalDate.now().plusDays(7)) // 7일 후 기한 설정
                .build();

        MatchEntity savedMatch = matchRepository.save(match);

        return MatchDTO.toMatchDTO(savedMatch);
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

    @Transactional
    public void cancelMatchIfDeadlineExceeded() {
        List<MatchEntity> expiredMatches = matchRepository.findByDeadlineBefore(LocalDate.now());
        for (MatchEntity match : expiredMatches) {
            matchRepository.delete(match); // DB에서 삭제
        }
    }

    @Scheduled(cron = "0 0 0 * * ?") // 매일 자정 실행
    public void checkMatchDeadlines() {
        cancelMatchIfDeadlineExceeded();
    }

    public void cancelMatch(int matchId) {
        matchRepository.delete(matchRepository.getReferenceById(matchId));
    }
}

