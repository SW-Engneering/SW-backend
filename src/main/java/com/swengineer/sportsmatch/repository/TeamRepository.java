package com.swengineer.sportsmatch.repository;

import com.swengineer.sportsmatch.entity.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface TeamRepository extends JpaRepository<TeamEntity, Integer> {

    Optional<TeamEntity> findById(Integer teamId);

}