package com.swengineer.sportsmatch.controller;

import com.swengineer.sportsmatch.dto.TeamDTO;
import com.swengineer.sportsmatch.entity.Team;
import com.swengineer.sportsmatch.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teams")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @PostMapping
    public Team createTeam(@RequestBody TeamDTO teamDTO) {
        return teamService.createTeam(teamDTO);
    }

    // 기타 엔드포인트들
}
