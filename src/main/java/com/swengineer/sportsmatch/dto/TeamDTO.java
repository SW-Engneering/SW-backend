package com.swengineer.sportsmatch.dto;

import com.swengineer.sportsmatch.entity.TeamEntity;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TeamDTO {

    private int teamId; // 팀 ID
    private String teamName; // 팀 이름
    private int leaderId; // 팀 리더 ID
    private List<Integer> memberIds; // 팀원 IDs

    // Entity -> DTO 변환 메서드
    public static TeamDTO toTeamDTO(TeamEntity teamEntity) {
        TeamDTO teamDTO = new TeamDTO();
        teamDTO.setTeamId(teamEntity.getTeamId());
        teamDTO.setTeamName(teamEntity.getTeamName());
        teamDTO.setLeaderId(teamEntity.getLeader().getUserId());
        teamDTO.setMemberIds(teamEntity.getTeamMembers().stream()
                .map(userEntity -> userEntity.getUserId())
                .collect(Collectors.toList()));
        return teamDTO;
    }
}
