//package com.swengineer.sportsmatch.service;
//
//import com.swengineer.sportsmatch.dto.MyPageDefaultDTO;
//import com.swengineer.sportsmatch.entity.UserEntity;
//import com.swengineer.sportsmatch.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class MyPageDefaultService {
//
//    @Autowired
//    private UserRepository userRepository; // UserRepository 사용
//
//    // Default Page: 닉네임과 포지션 반환
//    public MyPageDefaultDTO getDefaultPage(int userId) {
//        // 사용자 정보를 조회
//        UserEntity userEntity = userRepository.findById(userId)
//                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));
//
//        // UserEntity -> MyPageDefaultDTO 변환
//        MyPageDefaultDTO myPageDefaultDTO = new MyPageDefaultDTO();
//        myPageDefaultDTO.setNickname(userEntity.getNickname());
//        myPageDefaultDTO.setPosition(userEntity.getPosition());
//
//        return myPageDefaultDTO;
//    }
