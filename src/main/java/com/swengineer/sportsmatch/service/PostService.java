package com.swengineer.sportsmatch.service;

import com.swengineer.sportsmatch.dto.PostDTO;
import com.swengineer.sportsmatch.entity.Post;
import com.swengineer.sportsmatch.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public Post createPost(PostDTO postDTO) {
        Post post = new Post();
        post.setPostContent(postDTO.getPostContent());
        post.setPostImagePath(postDTO.getPostImagePath());
        post.setUserId(postDTO.getUserId());
        return postRepository.save(post);
    }

    public Optional<Post> getPostById(Integer postId) {
        return postRepository.findById(postId);
    }

    public Post updatePost(Integer postId, PostDTO postDTO) {
        Optional<Post> optionalPost = postRepository.findById(postId);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            post.setPostContent(postDTO.getPostContent());
            post.setPostImagePath(postDTO.getPostImagePath());
            return postRepository.save(post);
        }
        return null;
    }

    public void deletePost(Integer postId) {
        postRepository.deleteById(postId);
    }

    // 기타 서비스 메서드들
}
