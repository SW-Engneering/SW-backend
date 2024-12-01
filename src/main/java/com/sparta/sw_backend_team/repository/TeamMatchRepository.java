package com.sparta.sw_backend_team.repository;

import com.sparta.sw_backend_team.entity.TeamMatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamMatchRepository extends JpaRepository<TeamMatch, Integer> {
    // 특정 팀의 매칭 정보 검색
    List<TeamMatch> findByTeamId(int teamId);
}
