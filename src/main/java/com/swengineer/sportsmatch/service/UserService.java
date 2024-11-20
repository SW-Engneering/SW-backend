package com.swengineer.sportsmatch.service;

import com.swengineer.sportsmatch.doamin.UserRepository;
import com.swengineer.sportsmatch.dto.Response.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User save(User user) {
        return userRepository.save(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }
}

