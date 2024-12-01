package com.sparta.sw_backend_team.repository;

import com.sparta.sw_backend_team.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    // 특정 사용자의 게시글 검색
    List<Post> findByUserId(int userId);
}
