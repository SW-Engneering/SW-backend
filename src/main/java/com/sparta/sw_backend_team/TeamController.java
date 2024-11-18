package com.sparta.sw_backend_team;

import com.sparta.sw_backend_team.dto.TeamDto;
import com.sparta.sw_backend_team.dto.UserDto;
import com.sparta.sw_backend_team.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/teams")
public class TeamController {

    @Autowired
    private TeamService teamService;

    // 1) 팀 생성
    @PostMapping
    public ResponseEntity<TeamDto> createTeam(@RequestBody TeamDto teamDto) {
        try {
            TeamDto createdTeam = teamService.createTeam(teamDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdTeam);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "팀 생성 중 오류 발생", e);
        }
    }

    // 2) 팀 목록 조회
    @GetMapping
    public ResponseEntity<List<TeamDto>> listTeams() {
        try {
            List<TeamDto> teams = teamService.getAllTeams();
            return ResponseEntity.ok(teams);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "팀 목록 조회 중 오류 발생", e);
        }
    }

    // 3) 특정 팀 정보 조회
    @GetMapping("/{teamId}")
    public ResponseEntity<TeamDto> getTeamById(@PathVariable String teamId) {
        try {
            TeamDto team = teamService.getTeamById(teamId);
            return ResponseEntity.ok(team);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 팀을 찾을 수 없습니다", e);
        }
    }

    // 4) 팀 정보 수정
    @PutMapping("/{teamId}")
    public ResponseEntity<TeamDto> updateTeam(
            @PathVariable String teamId,
            @RequestBody TeamDto teamDto) {
        try {
            TeamDto updatedTeam = teamService.updateTeam(teamId, teamDto);
            return ResponseEntity.ok(updatedTeam);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 팀을 찾을 수 없습니다", e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "팀 수정 중 오류 발생", e);
        }
    }

    // 5) 팀 삭제
    @DeleteMapping("/{teamId}")
    public ResponseEntity<Void> deleteTeam(@PathVariable String teamId) {
        try {
            teamService.deleteTeam(teamId);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "삭제할 팀을 찾을 수 없습니다", e);
        }
    }

    // 6) 팀원 추가
    @PostMapping("/{teamId}/members")
    public ResponseEntity<Void> addTeamMember(
            @PathVariable String teamId,
            @RequestBody UserDto userDto) {
        try {
            teamService.addTeamMember(teamId, userDto);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 팀을 찾을 수 없습니다", e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "팀원 추가 중 오류 발생", e);
        }
    }

    // 7) 팀원 방출
    @DeleteMapping("/{teamId}/members")
    public ResponseEntity<Void> removeTeamMember(
            @PathVariable String teamId,
            @RequestParam Integer userId) {
        try {
            teamService.removeTeamMember(teamId, userId);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 팀 또는 사용자를 찾을 수 없습니다", e);
        }
    }

    // 8) 팀장 권한 양도
    @PutMapping("/{teamId}/leader")
    public ResponseEntity<Void> transferLeadership(
            @PathVariable String teamId,
            @RequestBody UserDto newLeaderDto) {
        try {
            teamService.transferLeadership(teamId, newLeaderDto.getUserId());
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 팀 또는 사용자를 찾을 수 없습니다", e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "팀장 권한 양도 중 오류 발생", e);
        }
    }

    // 9) 매칭 정보 조회
    @GetMapping("/{teamId}/match/{matchId}")
    public ResponseEntity<Object> getMatchInfo(
            @PathVariable String teamId,
            @PathVariable Integer matchId) {
        try {
            Object matchInfo = teamService.getMatchInfo(teamId, matchId);
            return ResponseEntity.ok(matchInfo);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 팀 또는 매칭 정보를 찾을 수 없습니다", e);
        }
    }

    // 10) 팀 공지 및 투표 작성
    @PostMapping("/{teamId}/notices")
    public ResponseEntity<Void> createNoticeOrPoll(
            @PathVariable String teamId,
            @RequestBody Object noticeOrPollDto) {
        try {
            teamService.createNoticeOrPoll(teamId, noticeOrPollDto);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 팀을 찾을 수 없습니다", e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "공지 또는 투표 생성 중 오류 발생", e);
        }
    }

    // 11) 팀 나가기
    @PostMapping("/{teamId}/leave")
    public ResponseEntity<Void> leaveTeam(
            @PathVariable String teamId,
            @RequestParam Integer userId) {
        try {
            teamService.leaveTeam(teamId, userId);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 팀 또는 사용자를 찾을 수 없습니다", e);
        }
    }

    // 12) 팀원 일괄 방출
    @PostMapping("/{teamId}/members/batchRemove")
    public ResponseEntity<Void> batchRemoveMembers(
            @PathVariable String teamId,
            @RequestBody List<Integer> userIds) {
        try {
            teamService.batchRemoveMembers(teamId, userIds);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 팀 또는 사용자를 찾을 수 없습니다", e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "팀원 일괄 방출 중 오류 발생", e);
        }
    }

}


