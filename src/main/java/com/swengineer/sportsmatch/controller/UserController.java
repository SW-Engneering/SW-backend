package com.swengineer.sportsmatch.controller;

import com.swengineer.sportsmatch.dto.UserDTO;
import com.swengineer.sportsmatch.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    // 회원가입
    @PostMapping("/register")
    @Operation(summary = "회원가입")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "회원가입 성공"),
            @ApiResponse(responseCode = "409", description = "이미 사용 중인 닉네임")
    })
    public ResponseEntity<UserDTO> register(@RequestBody UserDTO userDTO) {
        UserDTO registeredUser = userService.register(userDTO);
        return ResponseEntity.status(201).body(registeredUser);
    }

    // 로그인
    @PostMapping("/login")
    @Operation(summary = "로그인")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "로그인 성공"),
            @ApiResponse(responseCode = "401", description = "비밀번호가 일치하지 않음"),
            @ApiResponse(responseCode = "404", description = "닉네임을 가진 사용자를 찾을 수 없음")
    })
    public ResponseEntity<UserDTO> login(@RequestParam String nickname, @RequestParam String passwd) {
        UserDTO loggedInUser = userService.login(nickname, passwd);
        return ResponseEntity.status(200).body(loggedInUser);
    }
}
