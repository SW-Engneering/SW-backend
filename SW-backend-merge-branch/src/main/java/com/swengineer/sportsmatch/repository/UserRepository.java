package com.swengineer.sportsmatch.repository;

import com.swengineer.sportsmatch.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByNickname(String nickname); // 닉네임으로 사용자 조회
}
