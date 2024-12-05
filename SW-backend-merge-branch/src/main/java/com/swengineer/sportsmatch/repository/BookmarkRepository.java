package com.swengineer.sportsmatch.repository;

import com.swengineer.sportsmatch.entity.BookmarkEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookmarkRepository extends JpaRepository<BookmarkEntity, Integer> {

    // 특정 사용자의 즐겨찾기 목록 조회, 생성 시간 기준 내림차순 정렬
    @Query("SELECT b FROM BookmarkEntity b WHERE b.userEntity.userId = :userId ORDER BY b.createdTime DESC")
    List<BookmarkEntity> findBookmarksByUserIdOrderByCreatedTimeDesc(@Param("userId") int userId);

    // 특정 사용자와 게시글로 즐겨찾기 조회 (중복 방지용)
    @Query("SELECT b FROM BookmarkEntity b WHERE b.userEntity.userId = :userId AND b.boardEntity.post_id = :postId")
    List<BookmarkEntity> findByUserIdAndPostId(@Param("userId") int userId, @Param("postId") int postId);
}
