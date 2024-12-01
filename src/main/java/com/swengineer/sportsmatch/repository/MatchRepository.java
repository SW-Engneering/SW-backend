package com.swengineer.sportsmatch.repository;

import com.swengineer.sportsmatch.entity.MatchEntity;
import com.swengineer.sportsmatch.entity.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface MatchRepository extends JpaRepository<MatchEntity, Integer> {

    boolean existsByHomeTeamOrAwayTeam(TeamEntity homeTeam, TeamEntity homeTeam1);

    List<MatchEntity> findByIsCancelledFalseAndDeadlineBefore(LocalDate now);

    List<MatchEntity> findByDeadlineBefore(LocalDate now);
}
