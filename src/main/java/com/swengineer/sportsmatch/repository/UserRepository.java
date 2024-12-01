package com.swengineer.sportsmatch.repository;

import com.swengineer.sportsmatch.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

}