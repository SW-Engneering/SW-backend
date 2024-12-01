package com.swengineer.sportsmatch.repository;

import com.swengineer.sportsmatch.entity.TeamMemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamMemberRepository extends JpaRepository<TeamMemberEntity, Integer> {

//    // 특정 팀에 속한 멤버 조회
//    List<TeamMemberEntity> findByTeam_TeamId(int teamId);
//
//    // 특정 사용자와 관련된 팀 멤버 정보 조회
//    List<TeamMemberEntity> findByUser_UserId(int userId); // 기존 User_id -> UserId로 변경
//
//    // 역할별로 팀 멤버 조회
//    List<TeamMemberEntity> findByTeam_TeamIdAndRole(Integer teamId, TeamMemberEntity.Role role);
}
