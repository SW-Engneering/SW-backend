package com.swengineer.sportsmatch.service;

import com.swengineer.sportsmatch.dto.MatchDTO;
import com.swengineer.sportsmatch.dto.TeamDTO;
import com.swengineer.sportsmatch.dto.TeamMemberDTO;
import com.swengineer.sportsmatch.entity.MatchEntity;
import com.swengineer.sportsmatch.entity.TeamEntity;
import com.swengineer.sportsmatch.entity.TeamMemberEntity;
import com.swengineer.sportsmatch.entity.UserEntity;
import com.swengineer.sportsmatch.repository.MatchRepository;
import com.swengineer.sportsmatch.repository.TeamMemberRepository;
import com.swengineer.sportsmatch.repository.TeamRepository;
import com.swengineer.sportsmatch.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private TeamMemberRepository teamMemberRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MatchRepository matchRepository;

    // 1. 팀 생성
    public TeamDTO createTeam(TeamDTO teamDTO, int leaderId) {
        // 리더 유저 검색
        Optional<UserEntity> leaderOpt = userRepository.findById(leaderId);
        if (leaderOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "리더를 찾을 수 없습니다.");
        }
        UserEntity leader = leaderOpt.get();

        // 팀 생성 및 리더 설정
        TeamEntity teamEntity = new TeamEntity();
        teamEntity.setTeamName(teamDTO.getTeamName());
        teamEntity.setLeader(leader);

        // 팀 저장
        TeamEntity savedTeam = teamRepository.save(teamEntity);

        // 팀 멤버로 리더 추가
        TeamMemberEntity teamMember = new TeamMemberEntity();
        teamMember.setTeam(savedTeam);
        teamMember.setUser(leader);
        teamMember.setRole(TeamMemberEntity.Role.LEADER); // 역할 설정
        teamMemberRepository.save(teamMember);

        // 리더의 team_id 업데이트
        leader.setTeam(savedTeam);
        userRepository.save(leader);

        // 결과 반환
        return TeamDTO.toTeamDTO(savedTeam);
    }



    // 2. 팀 목록 조회
    public List<TeamDTO> getAllTeams() {
        List<TeamEntity> teamEntities = teamRepository.findAll();
        return teamEntities.stream()
                .map(TeamDTO::toTeamDTO)
                .collect(Collectors.toList());
    }

    // 3. 특정 팀 조회
    public TeamDTO getTeamById(int teamId) {
        Optional<TeamEntity> teamEntityOpt = teamRepository.findById(teamId);
        if (teamEntityOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "팀을 찾을 수 없습니다.");
        }
        return TeamDTO.toTeamDTO(teamEntityOpt.get());
    }

    // 4. 팀 수정
    public TeamDTO updateTeam(int teamId, TeamDTO teamDTO, int userId) {
        Optional<TeamEntity> teamEntityOpt = teamRepository.findById(teamId);
        if (teamEntityOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "팀을 찾을 수 없습니다.");
        }

        TeamEntity teamEntity = teamEntityOpt.get();
        if (teamEntity.getLeader().getUserId() != userId) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "수정 권한이 없습니다.");
        }

        teamEntity.setTeamName(teamDTO.getTeamName());
        teamRepository.save(teamEntity);

        return TeamDTO.toTeamDTO(teamEntity);
    }

    // 5. 팀 삭제
    public void deleteTeam(int teamId, int userId) {
        Optional<TeamEntity> teamEntityOpt = teamRepository.findById(teamId);
        if (teamEntityOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "팀을 찾을 수 없습니다.");
        }

        TeamEntity teamEntity = teamEntityOpt.get();
        if (teamEntity.getLeader().getUserId() != userId) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "삭제 권한이 없습니다.");
        }

        teamRepository.deleteById(teamId);
    }

    // 6. 팀원 추가
    @Transactional
    public TeamMemberDTO addTeamMember(int teamId, int userId) {
        // 팀 확인
        Optional<TeamEntity> teamEntityOpt = teamRepository.findById(teamId);
        if (teamEntityOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "팀을 찾을 수 없습니다.");
        }

        // 사용자 확인
        Optional<UserEntity> userEntityOpt = userRepository.findById(userId);
        if (userEntityOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다.");
        }

        TeamEntity teamEntity = teamEntityOpt.get();
        UserEntity userEntity = userEntityOpt.get();

        // 팀 멤버 추가
        TeamMemberEntity teamMemberEntity = new TeamMemberEntity();
        teamMemberEntity.setTeam(teamEntity);
        teamMemberEntity.setUser(userEntity);
        teamMemberEntity.setRole(TeamMemberEntity.Role.MEMBER);
        teamMemberRepository.save(teamMemberEntity);

        // 사용자 엔티티의 team_id 업데이트
        userEntity.setTeam(teamEntity);
        userRepository.save(userEntity);

        return TeamMemberDTO.toTeamMemberDTO(teamMemberEntity);
    }

    // 7. 팀원 방출
    public void removeTeamMember(int teamId, int userId, int leaderId) {
        Optional<TeamEntity> teamEntityOpt = teamRepository.findById(teamId);
        Optional<TeamMemberEntity> teamMemberEntityOpt = teamMemberRepository.findById(userId);

        if (teamEntityOpt.isEmpty() || teamMemberEntityOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "팀 또는 팀원을 찾을 수 없습니다.");
        }

        TeamEntity teamEntity = teamEntityOpt.get();
        if (teamEntity.getLeader().getUserId() != leaderId) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "방출 권한이 없습니다.");
        }

        teamMemberRepository.delete(teamMemberEntityOpt.get());
    }
    // 8) 팀장 권한 양도
    @Transactional
    public void transferLeadership(int teamId, int currentLeaderId, int newLeaderId) {
        Optional<TeamEntity> teamEntityOpt = teamRepository.findById(teamId);
        if (teamEntityOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "팀을 찾을 수 없습니다.");
        }

        TeamEntity teamEntity = teamEntityOpt.get();
        if (teamEntity.getLeader().getUserId() != currentLeaderId) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "권한이 없습니다.");
        }

        Optional<UserEntity> newLeaderOpt = userRepository.findById(newLeaderId);
        if (newLeaderOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "새 팀장을 찾을 수 없습니다.");
        }

        UserEntity newLeader = newLeaderOpt.get();

        // 1. 기존 리더의 역할 변경
        TeamMemberEntity currentLeader = teamMemberRepository.findByUser_UserId(currentLeaderId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "현재 팀원을 찾을 수 없습니다."));
        currentLeader.setRole(TeamMemberEntity.Role.MEMBER);
        teamMemberRepository.save(currentLeader);

        // 2. 새 리더의 역할 변경
        TeamMemberEntity newLeaderMember = teamMemberRepository.findByUser_UserId(newLeaderId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "새 팀원을 찾을 수 없습니다."));
        newLeaderMember.setRole(TeamMemberEntity.Role.LEADER);
        teamMemberRepository.save(newLeaderMember);

        // 3. 팀 엔티티의 리더 업데이트
        teamEntity.setLeader(newLeader);
        teamRepository.save(teamEntity);
    }


    // 9) 매칭 정보 조회
    public List<MatchDTO> getTeamMatches(int teamId) {
        Optional<TeamEntity> teamEntityOpt = teamRepository.findById(teamId);
        if (teamEntityOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "팀을 찾을 수 없습니다.");
        }

        TeamEntity teamEntity = teamEntityOpt.get();
        List<MatchEntity> matches = matchRepository.findByIsCancelledFalseAndDeadlineBefore(LocalDate.now());

        return matches.stream()
                .filter(match -> match.getHomeTeam().equals(teamEntity) || match.getAwayTeam().equals(teamEntity))
                .map(MatchDTO::toMatchDTO)
                .toList();
    }

    // 10) 팀 공지 및 투표 작성
    public void createAnnouncement(int teamId, int leaderId, String content) {
        Optional<TeamEntity> teamEntityOpt = teamRepository.findById(teamId);
        if (teamEntityOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "팀을 찾을 수 없습니다.");
        }

        TeamEntity teamEntity = teamEntityOpt.get();
        if (teamEntity.getLeader().getUserId() != leaderId) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "권한이 없습니다.");
        }
    }

    // 11) 팀 나가기
    @Transactional
    public void leaveTeam(int teamId, int userId) {
        // 팀 확인
        TeamEntity teamEntity = teamRepository.findById(teamId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "팀을 찾을 수 없습니다."));

        // 팀원 확인
        TeamMemberEntity teamMemberEntity = teamMemberRepository.findByUser_UserIdAndTeam_TeamId(userId, teamId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "팀원을 찾을 수 없습니다."));

        // 팀원 삭제
        teamMemberRepository.delete(teamMemberEntity);

        // 유저 엔티티의 team_id를 null로 초기화
        UserEntity userEntity = teamMemberEntity.getUser();
        userEntity.setTeam(null);
        userRepository.save(userEntity);
    }

}


