package com.swengineer.sportsmatch.controller;

import com.swengineer.sportsmatch.dto.InquiryDTO;
import com.swengineer.sportsmatch.service.InquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mypage/inquiry")
public class InquiryController {

    @Autowired
    private InquiryService inquiryService;

    // 문의 작성
    @PostMapping
    public void createInquiry(@RequestBody InquiryDTO inquiryDTO) {
        inquiryService.createInquiry(inquiryDTO);
    }

    // 문의 내역 조회
    @GetMapping("/{userId}")
    public List<InquiryDTO> getUserInquiries(@PathVariable int userId) {
        return inquiryService.getUserInquiries(userId);
    }

    // 문의 삭제
    @DeleteMapping("/{inquiryId}")
    public String deleteInquiry(@PathVariable Long inquiryId, @RequestParam int userId) {
        inquiryService.deleteInquiry(inquiryId, userId);
        return "삭제 성공";
    }
}



