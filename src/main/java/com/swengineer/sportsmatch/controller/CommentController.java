package com.swengineer.sportsmatch.controller;

import com.swengineer.sportsmatch.dto.CommentDTO;
import com.swengineer.sportsmatch.dto.BoardDTO;
import com.swengineer.sportsmatch.service.CommentService;
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
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private BoardService boardService; // 주입 누락된 boardService 추가

    // 댓글 생성
    @PostMapping
    @Operation(summary = "댓글 작성")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "댓글 작성 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청 (게시글 또는 사용자 정보를 찾을 수 없음)"),
            @ApiResponse(responseCode = "403", description = "댓글 작성 권한 없음")
    })
    public ResponseEntity<CommentDTO> createComment(
            @RequestBody CommentDTO commentDTO,
            @RequestParam int postId,
            @RequestParam int userId) {
        try {
            // 게시글 조회
            BoardDTO boardDTO = boardService.getBoardPost(postId);

            // 게시글 타입이 "match"일 경우 팀 리더인지 확인
            if ("match".equalsIgnoreCase(boardDTO.getPost_type())) {
                if (!boardService.isTeamLeader(userId)) {
                    throw new ResponseStatusException(HttpStatus.FORBIDDEN, "매칭 게시글에 댓글을 작성하려면 팀 리더여야 합니다.");
                }
            }

            // 댓글 작성
            CommentDTO createdComment = commentService.createComment(commentDTO, postId, userId);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdComment);
        } catch (ResponseStatusException e) {
            throw e; // ResponseStatusException은 그대로 반환
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "댓글 작성 중 오류 발생", e);
        }
    }

    // 댓글 조회 (게시글 ID 기준)
    @GetMapping
    @Operation(summary = "댓글 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "댓글 조회 성공"),
            @ApiResponse(responseCode = "404", description = "게시글을 찾을 수 없음")
    })
    public ResponseEntity<List<CommentDTO>> getCommentsByPost(@RequestParam int postId) {
        try {
            List<CommentDTO> comments = commentService.getCommentsByPost(postId);
            return ResponseEntity.status(HttpStatus.OK).body(comments);
        } catch (ResponseStatusException e) {
            throw e; // 기존 예외 유지
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "게시글을 찾을 수 없음", e);
        }
    }

    // 댓글 수정
    @PutMapping("/{commentId}")
    @Operation(summary = "댓글 수정")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "댓글 수정 성공"),
            @ApiResponse(responseCode = "404", description = "댓글을 찾을 수 없음"),
            @ApiResponse(responseCode = "403", description = "댓글 수정 권한 없음")
    })
    public ResponseEntity<CommentDTO> updateComment(
            @PathVariable int commentId,
            @RequestBody String updatedContent,
            @RequestParam int userId) {
        try {
            CommentDTO updatedComment = commentService.updateComment(commentId, updatedContent, userId);
            return ResponseEntity.status(HttpStatus.OK).body(updatedComment);
        } catch (ResponseStatusException e) {
            throw e; // 기존 예외 유지
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "댓글 수정 중 오류 발생", e);
        }
    }

    // 댓글 삭제
    @DeleteMapping("/{commentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "댓글 삭제")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "댓글 삭제 성공"),
            @ApiResponse(responseCode = "404", description = "댓글을 찾을 수 없음"),
            @ApiResponse(responseCode = "403", description = "댓글 삭제 권한 없음")
    })
    public ResponseEntity<String> deleteComment(@PathVariable int commentId, @RequestParam int userId) {
        try {
            commentService.deleteComment(commentId, userId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("댓글이 성공적으로 삭제되었습니다.");
        } catch (ResponseStatusException e) {
            throw e; // 기존 예외 유지
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "댓글 삭제 중 오류 발생", e);
        }
    }
}
