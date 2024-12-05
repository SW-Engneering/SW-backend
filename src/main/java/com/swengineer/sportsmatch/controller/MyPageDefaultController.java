//package com.swengineer.sportsmatch.controller;
//
//import com.swengineer.sportsmatch.dto.MyPageDefaultDTO;
//import com.swengineer.sportsmatch.service.MyPageDefaultService;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import io.swagger.v3.oas.annotations.responses.ApiResponses;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/myPage")
//public class MyPageDefaultController {
//
//    @Autowired
//    private MyPageDefaultService myPageDefaultService;
//
//    @Operation(summary = "Default Page", description = "유저 닉네임과 포지션을 포함한 기본 정보를 반환합니다.")
//    @ApiResponses({
//            @ApiResponse(responseCode = "200", description = "유저 기본 정보 반환 성공")
//    })
//    @GetMapping("/default")
//    public ResponseEntity<MyPageDefaultDTO> getDefaultPage(@RequestParam int userId) {
//        MyPageDefaultDTO defaultDTO = myPageDefaultService.getDefaultPage(userId);
//        return ResponseEntity.ok(defaultDTO);
//    }
//}
