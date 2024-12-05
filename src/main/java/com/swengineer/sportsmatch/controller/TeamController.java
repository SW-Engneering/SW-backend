package com.swengineer.sportsmatch.controller;

import com.swengineer.sportsmatch.dto.AnnouncementDTO;
import com.swengineer.sportsmatch.dto.MatchDTO;
import com.swengineer.sportsmatch.dto.TeamDTO;
import com.swengineer.sportsmatch.dto.TeamMemberDTO;
import com.swengineer.sportsmatch.service.TeamService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    // 1. 팀 생성
    @PostMapping
    @Operation(summary = "팀 생성", description = "새로운 팀을 생성합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "팀 생성 성공"),
            @ApiResponse(responseCode = "400", description = "해당 유저는 이미 팀에 가입되어 있음."),
            @ApiResponse(responseCode = "404", description = "리더를 찾을 수 없음")
    })
    public ResponseEntity<TeamDTO> createTeam(@RequestBody TeamDTO teamDTO, @RequestParam int leaderId) {
        TeamDTO createdTeam = teamService.createTeam(teamDTO, leaderId);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTeam);
    }

    // 2. 팀 목록 조회
    @GetMapping
    @Operation(summary = "팀 목록 조회", description = "모든 팀 목록을 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "팀 목록 조회 성공")
    })
    public ResponseEntity<List<TeamDTO>> getAllTeams() {
        List<TeamDTO> teams = teamService.getAllTeams();
        return ResponseEntity.status(HttpStatus.OK).body(teams);
    }

    // 3. 특정 팀 조회
    @GetMapping("/{teamId}")
    @Operation(summary = "특정 팀 조회", description = "팀 ID로 특정 팀을 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "팀 조회 성공"),
            @ApiResponse(responseCode = "404", description = "팀을 찾을 수 없음")
    })
    public ResponseEntity<TeamDTO> getTeamById(@PathVariable int teamId) {
        TeamDTO team = teamService.getTeamById(teamId);
        return ResponseEntity.status(HttpStatus.OK).body(team);
    }

    // 4. 팀 수정
    @PutMapping("/{teamId}")
    @Operation(summary = "팀 수정", description = "팀 이름을 수정합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "팀 수정 성공"),
            @ApiResponse(responseCode = "403", description = "수정 권한이 없음"),
            @ApiResponse(responseCode = "404", description = "팀을 찾을 수 없음")
    })
    public ResponseEntity<TeamDTO> updateTeam(@PathVariable int teamId, @RequestBody TeamDTO teamDTO, @RequestParam int userId) {
        TeamDTO updatedTeam = teamService.updateTeam(teamId, teamDTO, userId);
        return ResponseEntity.status(HttpStatus.OK).body(updatedTeam);
    }

    // 5. 팀 삭제
    @DeleteMapping("/{teamId}")
    @Operation(summary = "팀 삭제", description = "팀을 삭제합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "팀 삭제 성공"),
            @ApiResponse(responseCode = "403", description = "삭제 권한이 없음"),
            @ApiResponse(responseCode = "404", description = "팀을 찾을 수 없음")
    })
    public ResponseEntity<Void> deleteTeam(@PathVariable int teamId, @RequestParam int userId) {
        teamService.deleteTeam(teamId, userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    // 6. 팀원 추가
    @PostMapping("/{teamId}/members")
    @Operation(summary = "팀원 추가", description = "팀에 새로운 멤버를 추가합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "팀원 추가 성공"),
            @ApiResponse(responseCode = "404", description = "팀 또는 사용자를 찾을 수 없음")
    })
    public ResponseEntity<TeamMemberDTO> addTeamMember(@PathVariable int teamId, @RequestParam int userId) {
        TeamMemberDTO addedMember = teamService.addTeamMember(teamId, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedMember);
    }

    // 7. 팀원 방출
    @DeleteMapping("/{teamId}/members/{userId}")
    @Operation(summary = "팀원 방출", description = "팀에서 특정 멤버를 방출합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "팀원 방출 성공"),
            @ApiResponse(responseCode = "403", description = "방출 권한이 없음"),
            @ApiResponse(responseCode = "404", description = "팀 또는 팀원을 찾을 수 없음")
    })
    public ResponseEntity<Void> removeTeamMember(@PathVariable int teamId, @PathVariable int userId, @RequestParam int leaderId) {
        teamService.removeTeamMember(teamId, userId, leaderId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();


    }

    // 8) 팀장 권한 양도
    @PutMapping("/{teamId}/transfer-leadership")
    @Operation(summary = "팀장 권한 양도", description = "팀장 권한을 다른 사용자에게 양도합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "팀장 권한 양도 성공"),
            @ApiResponse(responseCode = "403", description = "권한 없음"),
            @ApiResponse(responseCode = "404", description = "팀 또는 새 팀장을 찾을 수 없음")
    })
    public ResponseEntity<Void> transferLeadership(
            @PathVariable int teamId,
            @RequestParam int currentLeaderId,
            @RequestParam int newLeaderId) {
        teamService.transferLeadership(teamId, currentLeaderId, newLeaderId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // 10) 팀 공지 및 투표 작성
    @PostMapping("/{teamId}/announcement")
    @Operation(summary = "팀 공지 작성", description = "팀 공지사항 또는 투표를 작성합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "공지 작성 성공"),
            @ApiResponse(responseCode = "403", description = "권한 없음"),
            @ApiResponse(responseCode = "404", description = "팀을 찾을 수 없음")
    })
    public ResponseEntity<Void> createAnnouncement(
            @PathVariable int teamId,
            @RequestParam int leaderId,
            @RequestBody AnnouncementDTO announcementDTO) {
        try {
            teamService.createAnnouncement(teamId, leaderId, announcementDTO);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (ResponseStatusException e) {
            throw e;  // 권한 없음이나 팀 찾을 수 없음 등의 예외를 그대로 전파
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "공지 작성 중 오류 발생", e);
        }
    }


    // 11) 팀 나가기
    @DeleteMapping("/{teamId}/leave")
    @Operation(summary = "팀 나가기", description = "사용자가 팀을 나갑니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "팀 나가기 성공"),
            @ApiResponse(responseCode = "404", description = "팀 또는 팀원을 찾을 수 없음")
    })
    public ResponseEntity<Void> leaveTeam(@PathVariable int teamId, @RequestParam int userId) {
        teamService.leaveTeam(teamId, userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    // 공지사항 목록 조회
    @GetMapping("/{teamId}/announcements")
    @Operation(summary = "팀 공지 목록 조회", description = "팀에 속한 모든 공지사항을 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "공지 목록 조회 성공"),
            @ApiResponse(responseCode = "404", description = "팀을 찾을 수 없음")
    })
    public ResponseEntity<List<AnnouncementDTO>> getAnnouncements(@PathVariable int teamId) {
        List<AnnouncementDTO> announcements = teamService.getAnnouncementsByTeamId(teamId);
        return ResponseEntity.ok(announcements);
    }

    // 공지사항 상세 조회
    @GetMapping("/announcement/{announcementId}")
    @Operation(summary = "공지 상세 조회", description = "특정 공지사항의 상세 정보를 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "공지 상세 조회 성공"),
            @ApiResponse(responseCode = "404", description = "공지를 찾을 수 없음")
    })
    public ResponseEntity<AnnouncementDTO> getAnnouncement(@PathVariable int announcementId) {
        AnnouncementDTO announcement = teamService.getAnnouncementById(announcementId);
        return ResponseEntity.ok(announcement);
    }

}
