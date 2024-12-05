package com.swengineer.sportsmatch.repository;

import com.swengineer.sportsmatch.entity.BoardEntity;
import com.swengineer.sportsmatch.entity.BookmarkEntity;
import com.swengineer.sportsmatch.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookmarkRepository extends JpaRepository<BookmarkEntity, Integer> {


    List<BookmarkEntity> findByUserEntity(UserEntity user);

    Optional<BookmarkEntity> findByUserEntityAndBoardEntity(UserEntity user, BoardEntity board);
}
