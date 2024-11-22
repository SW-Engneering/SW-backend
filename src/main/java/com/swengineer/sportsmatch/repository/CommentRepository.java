package com.swengineer.sportsmatch.repository;

import com.swengineer.sportsmatch.entity.CommentEntity;
import com.swengineer.sportsmatch.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {

}
