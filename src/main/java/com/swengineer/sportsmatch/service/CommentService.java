package com.swengineer.sportsmatch.service;

import com.swengineer.sportsmatch.dto.CommentDTO;
import com.swengineer.sportsmatch.entity.Comment;
import com.swengineer.sportsmatch.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public Comment createComment(CommentDTO commentDTO) {
        Comment comment = new Comment();
        comment.setPostId(commentDTO.getPostId());
        comment.setUserId(commentDTO.getUserId());
        comment.setCommentContent(commentDTO.getCommentContent());
        return commentRepository.save(comment);
    }

    public void deleteComment(Integer commentId) {
        commentRepository.deleteById(commentId);
    }

    // 기타 서비스 메서드들
}
