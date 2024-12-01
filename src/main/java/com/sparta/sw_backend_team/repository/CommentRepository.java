package com.sparta.sw_backend_team.repository;

import com.sparta.sw_backend_team.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    // 특정 게시글 ID로 댓글 검색
    List<Comment> findByPostId(int postId);
}
