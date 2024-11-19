package com.swengineer.sportsmatch.repository;

import com.swengineer.sportsmatch.entity.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookmarkRepository extends JpaRepository<Bookmark, Integer> {
}
