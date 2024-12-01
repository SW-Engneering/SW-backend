package com.swengineer.sportsmatch.controller;

import com.swengineer.sportsmatch.dto.BoardDTO;
import com.swengineer.sportsmatch.service.BoardService;
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
    public ResponseEntity<BoardDTO> createMemberPost(@RequestBody BoardDTO boardDTO, @RequestParam int userId) {
        try {
            BoardDTO createdBoard = boardService.createBoardPost(boardDTO, userId, "member");
            return ResponseEntity.status(HttpStatus.CREATED).body(createdBoard);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "팀원 구하기 게시글 작성 중 오류 발생", e);
        }
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
    public ResponseEntity<BoardDTO> getMemberPost(@PathVariable int postId) {
        try {
            BoardDTO boardDTO = boardService.getBoardPost(postId);
            return ResponseEntity.status(HttpStatus.OK).body(boardDTO);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "게시글을 찾을 수 없음", e);
        }
    }

    @PutMapping("/member/{postId}")
    @Operation(summary = "팀원 구하기 게시글 수정")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "팀원 구하기 게시글 수정 성공"),
            @ApiResponse(responseCode = "404", description = "게시글을 찾을 수 없음")
    })
    public ResponseEntity<BoardDTO> updateMemberPost(@PathVariable int postId, @RequestBody BoardDTO boardDTO) {
        try {
            BoardDTO updatedBoard = boardService.updateBoardPost(postId, boardDTO);
            return ResponseEntity.status(HttpStatus.OK).body(updatedBoard);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "게시글을 찾을 수 없음", e);
        }
    }

    @DeleteMapping("/member/{postId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "팀원 구하기 게시글 삭제")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "팀원 구하기 게시글 삭제 성공"),
            @ApiResponse(responseCode = "404", description = "게시글을 찾을 수 없음")
    })
    public ResponseEntity<String> deleteMemberPost(@PathVariable int postId) {
        try {
            boardService.deleteBoardPost(postId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("게시글이 성공적으로 삭제되었습니다.");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "게시글을 찾을 수 없음", e);
        }
    }

    // 팀 구하기 게시판
    @PostMapping("/team")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "팀 구하기 게시글 작성")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "팀 구하기 게시글 작성 성공"),
            @ApiResponse(responseCode = "404", description = "사용자를 찾을 수 없음")
    })
    public ResponseEntity<BoardDTO> createTeamPost(@RequestBody BoardDTO boardDTO, @RequestParam int userId) {
        try {
            BoardDTO createdBoard = boardService.createBoardPost(boardDTO, userId, "team");
            return ResponseEntity.status(HttpStatus.CREATED).body(createdBoard);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "팀 구하기 게시글 작성 중 오류 발생", e);
        }
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
    public ResponseEntity<BoardDTO> getTeamPost(@PathVariable int postId) {
        try {
            BoardDTO boardDTO = boardService.getBoardPost(postId);
            return ResponseEntity.status(HttpStatus.OK).body(boardDTO);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "게시글을 찾을 수 없음", e);
        }
    }

    @PutMapping("/team/{postId}")
    @Operation(summary = "팀 구하기 게시글 수정")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "팀 구하기 게시글 수정 성공"),
            @ApiResponse(responseCode = "404", description = "게시글을 찾을 수 없음")
    })
    public ResponseEntity<BoardDTO> updateTeamPost(@PathVariable int postId, @RequestBody BoardDTO boardDTO) {
        try {
            BoardDTO updatedBoard = boardService.updateBoardPost(postId, boardDTO);
            return ResponseEntity.status(HttpStatus.OK).body(updatedBoard);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "게시글을 찾을 수 없음", e);
        }
    }

    @DeleteMapping("/team/{postId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "팀 구하기 게시글 삭제")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "팀 구하기 게시글 삭제 성공"),
            @ApiResponse(responseCode = "404", description = "게시글을 찾을 수 없음")
    })
    public ResponseEntity<String> deleteTeamPost(@PathVariable int postId) {
        try {
            boardService.deleteBoardPost(postId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("게시글이 성공적으로 삭제되었습니다.");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "게시글을 찾을 수 없음", e);
        }
    }
}
