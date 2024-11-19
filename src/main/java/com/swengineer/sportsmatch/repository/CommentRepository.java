package com.swengineer.sportsmatch.repository;

import com.swengineer.sportsmatch.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
