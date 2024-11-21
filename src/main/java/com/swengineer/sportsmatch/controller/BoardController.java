package com.swengineer.sportsmatch.controller;

import com.swengineer.sportsmatch.dto.BoardDTO;
import com.swengineer.sportsmatch.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    // 모든 회원 게시글 조회
    @GetMapping("/member")
    public ResponseEntity<List<BoardDTO>> getAllMemberPosts() {
        List<BoardDTO> boardDTOList = boardService.member_findAll();
        return ResponseEntity.ok(boardDTOList);
    }

    // 회원 게시글 작성 폼(이제 UI가 아닌 POST 요청을 통해 처리합니다)
    @PostMapping("/member")
    public ResponseEntity<BoardDTO> createMemberPost(@RequestBody BoardDTO boardDTO) {
        boardDTO.setPost_type("member");
        boardService.save(boardDTO);
        return ResponseEntity.status(201).body(boardDTO); // 생성된 게시글 반환
    }

    // 특정 회원 게시글 조회
    @GetMapping("/member/{post_id}")
    public ResponseEntity<BoardDTO> getMemberPostById(@PathVariable Long post_id) {
        boardService.updateHits(post_id);
        BoardDTO boardDTO = boardService.findByPost_id(post_id);
        return ResponseEntity.ok(boardDTO);
    }

    // 회원 게시글 수정
    @PutMapping("/member/{post_id}")
    public ResponseEntity<BoardDTO> updateMemberPost(@PathVariable Long post_id, @RequestBody BoardDTO boardDTO) {
        boardDTO.setPost_id(post_id); // 기존 게시글 ID 설정
        BoardDTO updatedBoard = boardService.update(boardDTO);
        return ResponseEntity.ok(updatedBoard);
    }

    // 회원 게시글 삭제
    @DeleteMapping("/member/{post_id}")
    public ResponseEntity<Void> deleteMemberPost(@PathVariable Long post_id) {
        boardService.delete(post_id);
        return ResponseEntity.noContent().build(); // 성공적으로 삭제되었음을 나타냅니다.
    }

    // 모든 팀 게시글 조회
    @GetMapping("/team")
    public ResponseEntity<List<BoardDTO>> getAllTeamPosts() {
        List<BoardDTO> boardDTOList = boardService.team_findAll();
        return ResponseEntity.ok(boardDTOList);
    }

    // 팀 게시글 작성
    @PostMapping("/team")
    public ResponseEntity<BoardDTO> createTeamPost(@RequestBody BoardDTO boardDTO) {
        boardDTO.setPost_type("team");
        boardService.save(boardDTO);
        return ResponseEntity.status(201).body(boardDTO);
    }

    // 특정 팀 게시글 조회
    @GetMapping("/team/{post_id}")
    public ResponseEntity<BoardDTO> getTeamPostById(@PathVariable Long post_id) {
        boardService.updateHits(post_id);
        BoardDTO boardDTO = boardService.findByPost_id(post_id);
        return ResponseEntity.ok(boardDTO);
    }

    // 팀 게시글 수정
    @PutMapping("/team/{post_id}")
    public ResponseEntity<BoardDTO> updateTeamPost(@PathVariable Long post_id, @RequestBody BoardDTO boardDTO) {
        boardDTO.setPost_id(post_id); // 기존 게시글 ID 설정
        BoardDTO updatedBoard = boardService.update(boardDTO);
        return ResponseEntity.ok(updatedBoard);
    }

    // 팀 게시글 삭제
    @DeleteMapping("/team/{post_id}")
    public ResponseEntity<Void> deleteTeamPost(@PathVariable Long post_id) {
        boardService.delete(post_id);
        return ResponseEntity.noContent().build();
    }
}
