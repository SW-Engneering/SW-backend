package com.swengineer.sportsmatch.service;

import com.swengineer.sportsmatch.dto.TeamDTO;
import com.swengineer.sportsmatch.entity.Team;
import com.swengineer.sportsmatch.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    public Team createTeam(TeamDTO teamDTO) {
        Team team = new Team();
        team.setTeamName(teamDTO.getTeamName());
        team.setUserId(teamDTO.getUserId());
        return teamRepository.save(team);
    }

    // 기타 서비스 메서드들
}
