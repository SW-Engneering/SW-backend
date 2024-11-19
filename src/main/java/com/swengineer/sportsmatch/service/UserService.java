package com.swengineer.sportsmatch.service;

import com.swengineer.sportsmatch.dto.UserDTO;
import com.swengineer.sportsmatch.entity.User;
import com.swengineer.sportsmatch.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(UserDTO userDTO) {
        User user = new User();
        user.setPasswd(userDTO.getPasswd());
        user.setUserName(userDTO.getUserName());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setNickname(userDTO.getNickname());
        // 기타 필요한 필드 설정
        return userRepository.save(user);
    }

    // 기타 서비스 메서드들
}
