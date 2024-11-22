package com.swengineer.sportsmatch.controller;

import com.swengineer.sportsmatch.dto.CommentDTO;
import com.swengineer.sportsmatch.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

}
