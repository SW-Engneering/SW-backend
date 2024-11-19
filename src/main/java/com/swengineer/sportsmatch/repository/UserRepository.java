package com.swengineer.sportsmatch.repository;

import com.swengineer.sportsmatch.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
