package com.swengineer.sportsmatch.controller;

import com.swengineer.sportsmatch.dto.BoardDTO;
import com.swengineer.sportsmatch.service.BookmarkService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookmarks")
public class BookmarkController {

    @Autowired
    private BookmarkService bookmarkService;

    // 즐겨찾기 추가
    @PostMapping
    @Operation(summary = "즐겨찾기 추가", description = "특정 게시글을 즐겨찾기에 추가합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "즐겨찾기 추가 성공"),
            @ApiResponse(responseCode = "404", description = "사용자 또는 게시글을 찾을 수 없음"),
            @ApiResponse(responseCode = "409", description = "이미 존재하는 즐겨찾기")
    })
    public ResponseEntity<Void> addBookmark(@RequestParam int userId, @RequestParam int postId) {
        bookmarkService.addBookmark(userId, postId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // 즐겨찾기 삭제
    @DeleteMapping
    @Operation(summary = "즐겨찾기 삭제", description = "특정 게시글을 즐겨찾기에서 삭제합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "즐겨찾기 삭제 성공"),
            @ApiResponse(responseCode = "404", description = "즐겨찾기를 찾을 수 없음")
    })
    public ResponseEntity<Void> removeBookmark(@RequestParam int userId, @RequestParam int postId) {
        bookmarkService.removeBookmark(userId, postId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    // 즐겨찾기 목록 조회
    @GetMapping
    @Operation(summary = "즐겨찾기 목록 조회", description = "사용자가 즐겨찾기한 게시글 목록을 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "즐겨찾기 목록 조회 성공")
    })
    public ResponseEntity<List<BoardDTO>> getBookmarks(@RequestParam int userId) {
        List<BoardDTO> bookmarks = bookmarkService.getBookmarks(userId);
        return ResponseEntity.ok(bookmarks);
    }
}
