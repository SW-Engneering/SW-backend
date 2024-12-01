package com.sparta.sw_backend_team.service;

import com.sparta.sw_backend_team.dto.TeamDto;
import com.sparta.sw_backend_team.dto.UserDto;
import com.sparta.sw_backend_team.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {

    private final List<TeamDto> teams = new ArrayList<>();

    @Override
    public TeamDto createTeam(TeamDto teamDto) {
        teamDto.setTeamId("team-" + (teams.size() + 1));
        teams.add(teamDto);
        return teamDto;
    }

    @Override
    public List<TeamDto> getAllTeams() {
        return teams;
    }

    @Override
    public TeamDto getTeamById(int teamId) {
        return teams.stream()
                .filter(team -> team.getTeamId().equals(teamId))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Team with ID " + teamId + " not found."));
    }

    @Override
    public TeamDto updateTeam(int teamId, TeamDto teamDto) {
        TeamDto existingTeam = getTeamById(teamId);
        existingTeam.setTeamName(teamDto.getTeamName());
        existingTeam.setTeamRegion(teamDto.getTeamRegion());
        return existingTeam;
    }

    @Override
    public void deleteTeam(int teamId) {
        TeamDto team = getTeamById(teamId);
        teams.remove(team);
    }

    @Override
    public void addTeamMember(int teamId, UserDto userDto) {
        TeamDto team = getTeamById(teamId);
        team.getMembers().add(userDto);
    }

    @Override
    public void removeTeamMember(int teamId, int userId) {
        TeamDto team = getTeamById(teamId);
        team.getMembers().removeIf(member -> member.getUserId() == (userId));
    }

    @Override
    public void transferLeadership(int teamId, Integer newLeaderId) {
        TeamDto team = getTeamById(teamId);
        team.setLeaderId(newLeaderId);
    }

    @Override
    public Object getMatchInfo(int teamId, Integer matchId) {
        // Mock implementation for now
        return "Match info for team " + teamId + " and match " + matchId;
    }

    @Override
    public void createNoticeOrPoll(int teamId, Object noticeOrPollDto) {
        // Mock implementation for now
    }

    @Override
    public void leaveTeam(int teamId, int userId) {
        removeTeamMember(teamId, userId);
    }

    @Override
    public void batchRemoveMembers(int teamId, List<Integer> userIds) {
        TeamDto team = getTeamById(teamId);

        // userId : integer -> int
        userIds.forEach(userId -> {
            int primitiveUserId = userId; // Unboxing (Integer -> int)
            team.getMembers().removeIf(member -> member.getUserId() == primitiveUserId);
        });
    }

}
