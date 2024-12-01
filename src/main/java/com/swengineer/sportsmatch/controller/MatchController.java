package com.swengineer.sportsmatch.controller;

import com.swengineer.sportsmatch.dto.MatchDTO;
import com.swengineer.sportsmatch.service.MatchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teamMatch")
public class MatchController {

    @Autowired
    private MatchService matchService;

    @PostMapping
    @Operation(summary = "매치 생성", description = "새 매치를 생성합니다. 매치 데이터와 팀 ID를 입력해야 합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "매치 생성 성공",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = MatchDTO.class))),
            @ApiResponse(responseCode = "400", description = "잘못된 요청", content = @Content),
            @ApiResponse(responseCode = "404", description = "팀을 찾을 수 없음", content = @Content),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류", content = @Content)
    })
    public MatchDTO createMatch(
            @Valid @RequestBody MatchDTO matchDTO,
            @RequestParam @io.swagger.v3.oas.annotations.Parameter(description = "홈 팀 ID") int homeTeamId,
            @RequestParam @io.swagger.v3.oas.annotations.Parameter(description = "원정 팀 ID") int awayTeamId) {
        return matchService.createMatch(matchDTO, homeTeamId, awayTeamId);
    }

    @PutMapping("/{matchId}")
    @Operation(summary = "매치 수정", description = "매치를 수정합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "매치 수정 성공",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = MatchDTO.class))),
            @ApiResponse(responseCode = "400", description = "잘못된 요청", content = @Content),
            @ApiResponse(responseCode = "404", description = "매치를 찾을 수 없음", content = @Content),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류", content = @Content)
    })
    public MatchDTO updateMatch(
            @PathVariable int matchId,
            @RequestBody MatchDTO matchDTO) {
        return matchService.updateMatch(matchId, matchDTO);
    }

    @DeleteMapping("/{matchId}")
    @Operation(summary = "매치 취소", description = "특정 매치를 취소합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "매치 취소 성공"),
            @ApiResponse(responseCode = "404", description = "매치를 찾을 수 없음"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    public ResponseEntity<Void> cancelMatch(@PathVariable int matchId) {
        matchService.cancelMatch(matchId);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Void> cancelExpiredMatches() {
        matchService.cancelMatchIfDeadlineExceeded();
        return ResponseEntity.ok().build();
    }
}
