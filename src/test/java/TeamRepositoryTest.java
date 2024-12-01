package com.sparta.sw_backend_team;


import com.sparta.sw_backend_team.entity.Team;
import com.sparta.sw_backend_team.repository.TeamRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class TeamRepositoryTest {

    @Autowired
    private TeamRepository teamRepository;

    @Test
    public void testSaveAndFindTeam() {
        // 데이터 저장 테스트
        Team team = new Team();
        team.setTeamName("Test Team");
        team.setUserId(1);
        teamRepository.save(team);

        // 데이터 조회 테스트
        List<Team> teams = teamRepository.findByTeamName("Test Team");

        assertThat(teams).isNotEmpty();
        assertThat(teams.get(0).getTeamName()).isEqualTo("Test Team");
    }
}
