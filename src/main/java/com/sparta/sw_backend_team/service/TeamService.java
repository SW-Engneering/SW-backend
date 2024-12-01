package com.sparta.sw_backend_team.service;

import com.sparta.sw_backend_team.dto.TeamDto;
import com.sparta.sw_backend_team.dto.UserDto;

import java.util.List;

public interface TeamService {
    // 팀 생성
    TeamDto createTeam(TeamDto teamDto);

    // 모든 팀 조회
    List<TeamDto> getAllTeams();

    // 특정 팀 조회
    TeamDto getTeamById(int teamId);

    // 팀 정보 수정
    TeamDto updateTeam(int teamId, TeamDto teamDto);

    // 팀 삭제
    void deleteTeam(int teamId);

    // 팀원 추가
    void addTeamMember(int teamId, UserDto userDto);

    // 팀원 제거
    void removeTeamMember(int teamId, int userId);

    // 팀장 권한 양도
    void transferLeadership(int teamId, Integer newLeaderId);

    // 매칭 정보 조회
    Object getMatchInfo(int teamId, Integer matchId);

    // 공지사항 또는 투표 생성
    void createNoticeOrPoll(int teamId, Object noticeOrPollDto);

    // 팀 나가기
    void leaveTeam(int teamId, int userId);

    // 팀원 일괄 방출
    void batchRemoveMembers(int teamId, List<Integer> userIds);
}
