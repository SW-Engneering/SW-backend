package com.swengineer.sportsmatch.controller;

import com.swengineer.sportsmatch.dto.InquiryDTO;
import com.swengineer.sportsmatch.service.InquiryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/myPage/inquiry")
public class InquiryController {

    @Autowired
    private InquiryService inquiryService;

    // 문의 작성
    @PostMapping
    @Operation(summary = "문의 생성")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "문의 생성 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청")
    })
    public ResponseEntity<String> createInquiry(@RequestBody InquiryDTO inquiryDTO) {
        try {
            inquiryService.createInquiry(inquiryDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("문의 생성 성공");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("문의 생성 실패: " + e.getMessage());
        }
    }

    // 문의 내역 조회
    @GetMapping("/{userId}")
    @Operation(summary = "특정 유저의 문의 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "문의 조회 성공"),
            @ApiResponse(responseCode = "404", description = "유저를 찾을 수 없음")
    })
    public ResponseEntity<List<InquiryDTO>> getUserInquiries(@PathVariable int userId) {
        try {
            List<InquiryDTO> inquiries = inquiryService.getUserInquiries(userId);
            return ResponseEntity.ok(inquiries);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // 문의 삭제
    @DeleteMapping("/{inquiryId}")
    @Operation(summary = "문의 삭제")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "문의 삭제 성공"),
            @ApiResponse(responseCode = "403", description = "삭제 권한 없음"),
            @ApiResponse(responseCode = "404", description = "문의 내용을 찾을 수 없음")
    })
    public ResponseEntity<String> deleteInquiry(@PathVariable Long inquiryId, @RequestParam int userId) {
        try {
            inquiryService.deleteInquiry(inquiryId, userId);
            return ResponseEntity.ok("문의 삭제 성공");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("삭제 권한이 없습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("문의 삭제 실패: " + e.getMessage());
        }
    }
}
