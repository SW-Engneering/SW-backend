package com.sparta.sw_backend_team.repository;

import com.sparta.sw_backend_team.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team, Integer> {
    // 팀 이름으로 검색
    List<Team> findByTeamName(String teamName);
}

