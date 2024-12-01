package com.sparta.sw_backend_team.repository;

import com.sparta.sw_backend_team.entity.TeamMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamMemberRepository extends JpaRepository<TeamMember, Integer> {
    // 특정 팀의 멤버 검색
    List<TeamMember> findByTeamId(int teamId);
}

