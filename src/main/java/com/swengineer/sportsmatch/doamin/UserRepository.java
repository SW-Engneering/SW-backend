package com.swengineer.sportsmatch.doamin;

import com.swengineer.sportsmatch.dto.Response.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}

