package com.swengineer.sportsmatch.repository;

import com.swengineer.sportsmatch.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

    @Modifying
    @Query(value = "update BoardEntity b set b.post_hits=b.post_hits+1 where b.post_id=:post_id")
    void updateHits(@Param("post_id") Long post_id);

}
