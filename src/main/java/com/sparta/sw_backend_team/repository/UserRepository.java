package com.sparta.sw_backend_team.repository;

import com.sparta.sw_backend_team.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    // 닉네임으로 사용자 검색
    List<User> findByNickname(String nickname);
}

