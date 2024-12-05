package com.swengineer.sportsmatch.controller;

import com.swengineer.sportsmatch.dto.MatchDTO;
import com.swengineer.sportsmatch.service.MatchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/matches")
public class MatchController {

    @Autowired
    private MatchService matchService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "매치 생성")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "매치 생성 성공"),
            @ApiResponse(responseCode = "404", description = "팀을 찾을 수 없음")
    })
    public ResponseEntity<MatchDTO> createMatch(@RequestParam int homeTeamId, @RequestParam int awayTeamId, @RequestBody MatchDTO matchDTO) {
        MatchDTO createdMatch = matchService.createMatch(homeTeamId, awayTeamId, matchDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMatch);
    }

    @GetMapping
    @Operation(summary = "모든 매치 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "매치 목록 조회 성공")
    })
    public List<MatchDTO> getAllMatches() {
        return matchService.getAllMatches();
    }

    @DeleteMapping("/{matchId}/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "매치 삭제")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "매치 삭제 성공"),
            @ApiResponse(responseCode = "404", description = "매치를 찾을 수 없음"),
            @ApiResponse(responseCode = "403", description = "팀장만 매치를 삭제할 수 있음")
    })
    public ResponseEntity<Void> deleteMatch(@PathVariable int matchId) {
        // 매치 삭제 처리
        matchService.deleteMatch(matchId);
        return ResponseEntity.noContent().build();
    }

}
