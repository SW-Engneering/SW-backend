package com.swengineer.sportsmatch.repository;

import com.swengineer.sportsmatch.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository extends JpaRepository<Match, Integer> {
}
