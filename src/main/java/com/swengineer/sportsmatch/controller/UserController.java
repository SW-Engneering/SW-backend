package com.swengineer.sportsmatch.controller;

import com.swengineer.sportsmatch.dto.UserDTO;
import com.swengineer.sportsmatch.entity.User;
import com.swengineer.sportsmatch.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public User createUser(@RequestBody UserDTO userDTO) {
        return userService.createUser(userDTO);
    }

    // 기타 엔드포인트들
}
