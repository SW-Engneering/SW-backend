package com.swengineer.sportsmatch.controller;

import com.swengineer.sportsmatch.dto.PostDTO;
import com.swengineer.sportsmatch.entity.Post;
import com.swengineer.sportsmatch.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping
    public Post createPost(@RequestBody PostDTO postDTO) {
        return postService.createPost(postDTO);
    }

    @GetMapping("/{postId}")
    public Post getPost(@PathVariable Integer postId) {
        return postService.getPostById(postId).orElse(null);
    }

    @PutMapping("/{postId}")
    public Post updatePost(@PathVariable Integer postId, @RequestBody PostDTO postDTO) {
        return postService.updatePost(postId, postDTO);
    }

    @DeleteMapping("/{postId}")
    public void deletePost(@PathVariable Integer postId) {
        postService.deletePost(postId);
    }
}
