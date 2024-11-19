package com.swengineer.sportsmatch.controller;

import com.swengineer.sportsmatch.dto.CommentDTO;
import com.swengineer.sportsmatch.entity.Comment;
import com.swengineer.sportsmatch.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts/{postId}/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping
    public Comment createComment(@PathVariable Integer postId, @RequestBody CommentDTO commentDTO) {
        commentDTO.setPostId(postId);
        return commentService.createComment(commentDTO);
    }

    @DeleteMapping("/{commentId}")
    public void deleteComment(@PathVariable Integer commentId) {
        commentService.deleteComment(commentId);
    }
}
