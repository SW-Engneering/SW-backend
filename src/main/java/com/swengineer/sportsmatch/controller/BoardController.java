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

    @DeleteMapping("/post/{postId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "게시글 삭제")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "팀 구하기 게시글 삭제 성공"),
            @ApiResponse(responseCode = "404", description = "게시글을 찾을 수 없음")
    })
    public ResponseEntity<String> deletePost(@PathVariable int postId, @RequestParam int userId) {
        try {
            boardService.deleteBoardPost(postId, userId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("게시글이 성공적으로 삭제되었습니다.");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "게시글 삭제 중 오류 발생", e);
        }
    }

    @PutMapping("/post/{postId}")
    @Operation(summary = "게시글 수정")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "매치 구하기 게시글 수정 성공"),
            @ApiResponse(responseCode = "404", description = "게시글을 찾을 수 없음")
    })
    public ResponseEntity<BoardDTO> updatePost(@PathVariable int postId, @RequestBody BoardDTO boardDTO, @RequestParam int userId) {
        try {
            BoardDTO updatedBoard = boardService.updateBoardPost(postId, boardDTO, userId);
            return ResponseEntity.status(HttpStatus.OK).body(updatedBoard);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "게시글 삭제 중 오류 발생", e);
        }
    }

    // 매칭 구하기 게시판
    @PostMapping("/match")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "매치 구하기 게시글 작성")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "매칭 구하기 게시글 작성 성공"),
            @ApiResponse(responseCode = "404", description = "사용자를 찾을 수 없음"),
            @ApiResponse(responseCode = "403", description = "매칭 게시글에 글을 작성하려면 팀 리더여야 합니다.")
    })
    public ResponseEntity<BoardDTO> createMatchPost(@RequestBody BoardDTO boardDTO, @RequestParam int userId) {
        try {
            if (!boardService.isTeamLeader(userId)) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "매칭 게시글에 글을 작성하려면 팀 리더여야 합니다.");
            }

            BoardDTO createdBoard = boardService.createBoardPost(boardDTO, userId, "match");
            return ResponseEntity.status(HttpStatus.CREATED).body(createdBoard);
        } catch (ResponseStatusException e) {
            // ResponseStatusException을 그대로 전파
            throw e;
        } catch (Exception e) {
            // 기타 예외는 BAD_REQUEST로 래핑
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "매칭 구하기 게시글 작성 중 오류 발생", e);
        }
    }

    @GetMapping("/match")
    @Operation(summary = "매치 구하기 게시글 목록 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "매치 구하기 게시글 목록 조회 성공")
    })
    public List<BoardDTO> getMatchPosts() {
        return boardService.getBoardPosts("Match");
    }

    @GetMapping("/match/{postId}")
    @Operation(summary = "매치 구하기 게시글 상세 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "매치 구하기 게시글 상세 조회 성공"),
            @ApiResponse(responseCode = "404", description = "게시글을 찾을 수 없음")
    })
    public ResponseEntity<BoardDTO> getMatchingPost(@PathVariable int postId) {
        try {
            BoardDTO boardDTO = boardService.getBoardPost(postId);
            return ResponseEntity.status(HttpStatus.OK).body(boardDTO);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "게시글을 찾을 수 없음", e);
        }
    }



    // 특정 유저의 게시글 조회
    @GetMapping("/board/{userId}")
    @Operation(summary = "특정 유저의 게시글 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "특정 유저의 게시글 조회 성공"),
            @ApiResponse(responseCode = "404", description = "유저를 찾을 수 없음")
    })
    public ResponseEntity<List<BoardDTO>> getBoardsByUser(@PathVariable int userId) {
        List<BoardDTO> boards = boardService.getBoardsByUser(userId);
        return ResponseEntity.ok(boards);
    }
}
