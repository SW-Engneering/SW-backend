package com.swengineer.sportsmatch.controller;

import com.swengineer.sportsmatch.dto.BoardDTO;
import com.swengineer.sportsmatch.service.BoardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BoardController {

    @Autowired
    private BoardService boardService;

    // 팀원 구하기 게시판
    @PostMapping("/member")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "팀원 구하기 게시글 작성")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "팀원 구하기 게시글 작성 성공"),
            @ApiResponse(responseCode = "404", description = "사용자를 찾을 수 없음")
    })
    public BoardDTO createMemberPost(@RequestBody BoardDTO boardDTO, @RequestParam int userId) {
        return boardService.createBoardPost(boardDTO, userId, "member");
    }

    @GetMapping("/member")
    @Operation(summary = "팀원 구하기 게시글 목록 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "팀원 구하기 게시글 목록 조회 성공")
    })
    public List<BoardDTO> getMemberPosts() {
        return boardService.getBoardPosts("member");
    }

    @GetMapping("/member/{postId}")
    @Operation(summary = "팀원 구하기 게시글 상세 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "팀원 구하기 게시글 상세 조회 성공"),
            @ApiResponse(responseCode = "404", description = "게시글을 찾을 수 없음")
    })
    public BoardDTO getMemberPost(@PathVariable int postId) {
        return boardService.getBoardPost(postId);
    }

    @PutMapping("/member/{postId}")
    @Operation(summary = "팀원 구하기 게시글 수정")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "팀원 구하기 게시글 수정 성공"),
            @ApiResponse(responseCode = "404", description = "게시글을 찾을 수 없음")
    })
    public BoardDTO updateMemberPost(@PathVariable int postId, @RequestBody BoardDTO boardDTO) {
        return boardService.updateBoardPost(postId, boardDTO);
    }

    @DeleteMapping("/member/{postId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "팀원 구하기 게시글 삭제")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "팀원 구하기 게시글 삭제 성공"),
            @ApiResponse(responseCode = "404", description = "게시글을 찾을 수 없음")
    })
    public void deleteMemberPost(@PathVariable int postId) {
        boardService.deleteBoardPost(postId);
    }

    // 팀 구하기 게시판
    @PostMapping("/team")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "팀 구하기 게시글 작성")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "팀 구하기 게시글 작성 성공"),
            @ApiResponse(responseCode = "404", description = "사용자를 찾을 수 없음")
    })
    public BoardDTO createTeamPost(@RequestBody BoardDTO boardDTO, @RequestParam int userId) {
        return boardService.createBoardPost(boardDTO, userId, "team");
    }

    @GetMapping("/team")
    @Operation(summary = "팀 구하기 게시글 목록 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "팀 구하기 게시글 목록 조회 성공")
    })
    public List<BoardDTO> getTeamPosts() {
        return boardService.getBoardPosts("team");
    }

    @GetMapping("/team/{postId}")
    @Operation(summary = "팀 구하기 게시글 상세 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "팀 구하기 게시글 상세 조회 성공"),
            @ApiResponse(responseCode = "404", description = "게시글을 찾을 수 없음")
    })
    public BoardDTO getTeamPost(@PathVariable int postId) {
        return boardService.getBoardPost(postId);
    }

    @PutMapping("/team/{postId}")
    @Operation(summary = "팀 구하기 게시글 수정")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "팀 구하기 게시글 수정 성공"),
            @ApiResponse(responseCode = "404", description = "게시글을 찾을 수 없음")
    })
    public BoardDTO updateTeamPost(@PathVariable int postId, @RequestBody BoardDTO boardDTO) {
        return boardService.updateBoardPost(postId, boardDTO);
    }

    @DeleteMapping("/team/{postId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "팀 구하기 게시글 삭제")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "팀 구하기 게시글 삭제 성공"),
            @ApiResponse(responseCode = "404", description = "게시글을 찾을 수 없음")
    })
    public void deleteTeamPost(@PathVariable int postId) {
        boardService.deleteBoardPost(postId);
    }
}
