package com.swengineer.sportsmatch.dto;

import com.swengineer.sportsmatch.entity.TeamMemberEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TeamMemberDTO {

    private int userId; // 사용자 ID
    private String userName; // 사용자 이름
    private String role; // 역할 (LEADER, MEMBER)
    private LocalDateTime joinedAt; // 가입 날짜

    // Entity -> DTO 변환 메서드
    public static TeamMemberDTO toTeamMemberDTO(TeamMemberEntity teamMemberEntity) {
        TeamMemberDTO teamMemberDTO = new TeamMemberDTO();
        teamMemberDTO.setUserId(teamMemberEntity.getUser().getUserId());
        teamMemberDTO.setUserName(teamMemberEntity.getUser().getUserName());
        teamMemberDTO.setRole(teamMemberEntity.getRole().name());
        teamMemberDTO.setJoinedAt(teamMemberEntity.getJoinedAt());
        return teamMemberDTO;
    }
}
