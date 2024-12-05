package com.swengineer.sportsmatch.repository;

import com.swengineer.sportsmatch.entity.MatchEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository extends JpaRepository<MatchEntity, Integer> {
}
