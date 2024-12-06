package com.swengineer.sportsmatch.repository;

import com.swengineer.sportsmatch.entity.MatchEntity;
import com.swengineer.sportsmatch.entity.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MatchRepository extends JpaRepository<MatchEntity, Integer> {

    @Query("SELECT CASE WHEN COUNT(m) > 0 THEN TRUE ELSE FALSE END FROM MatchEntity m WHERE m.homeTeam = :homeTeam OR m.awayTeam = :awayTeam")
    boolean existsByHomeTeamOrAwayTeam(@Param("homeTeam") TeamEntity homeTeam, @Param("awayTeam") TeamEntity awayTeam);

    List<MatchEntity> findByHomeTeam_TeamIdOrAwayTeam_TeamId(int homeTeamId, int awayTeamId);

}
