package com.swengineer.sportsmatch.repository;

import com.swengineer.sportsmatch.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<BoardEntity, Integer> {
    @Query("SELECT b FROM BoardEntity b WHERE b.post_type = :post_type")
    List<BoardEntity> findByPost_type(@Param("post_type") String post_type);  // 게시판 타입에 따른 게시글 조회

    List<BoardEntity> findByUserEntity_UserId(int userId);
}
