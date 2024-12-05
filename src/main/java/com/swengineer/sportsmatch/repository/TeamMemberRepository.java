package com.swengineer.sportsmatch.repository;

import com.swengineer.sportsmatch.entity.TeamMemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TeamMemberRepository extends JpaRepository<TeamMemberEntity, Integer> {
    Optional<TeamMemberEntity> findByUser_UserId(int userId);

    void deleteByUser_UserId(int userId);



}