package com.sparta.sw_backend_team.repository;

import com.sparta.sw_backend_team.entity.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, Integer> {
    // 추가적으로 필요한 메서드를 작성할 수 있습니다.
}
