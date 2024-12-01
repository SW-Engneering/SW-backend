package com.swengineer.sportsmatch.repository;

import com.swengineer.sportsmatch.entity.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface TeamRepository extends JpaRepository<TeamEntity, Integer> {

    Optional<TeamEntity> findById(Integer teamId);

    // 리더 ID로 팀 검색
    List<TeamEntity> findByLeaderUserId(int userId);

    // 팀 이름으로 검색
    List<TeamEntity> findByTeamName(String teamName);

}