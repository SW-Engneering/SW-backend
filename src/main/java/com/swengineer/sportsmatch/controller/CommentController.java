package com.swengineer.sportsmatch.controller;

import com.swengineer.sportsmatch.dto.CommentDTO;
import com.swengineer.sportsmatch.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    // 댓글 생성
    @PostMapping
    @Operation(summary = "댓글 작성")
    public CommentDTO createComment(
            @RequestBody CommentDTO commentDTO,
            @RequestParam int postId,
            @RequestParam int userId) {
        return commentService.createComment(commentDTO, postId, userId);
    }

    // 댓글 조회 (게시글 ID 기준)
    @GetMapping
    @Operation(summary = "댓글 조회")
    public List<CommentDTO> getCommentsByPost(@RequestParam int postId) {
        return commentService.getCommentsByPost(postId);
    }

    // 댓글 수정
    @PutMapping("/{commentId}")
    @Operation(summary = "댓글 수정")
    public CommentDTO updateComment(
            @PathVariable int commentId,
            @RequestBody String updatedContent) {
        return commentService.updateComment(commentId, updatedContent);
    }

    // 댓글 삭제
    @DeleteMapping("/{commentId}")
    @Operation(summary = "댓글 삭제")
    public ResponseEntity<String> deleteComment(@PathVariable int commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.ok("댓글이 성공적으로 삭제되었습니다.");
    }
}