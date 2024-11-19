package com.swengineer.sportsmatch.repository;

import com.swengineer.sportsmatch.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Integer> {
}
