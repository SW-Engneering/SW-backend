package com.swengineer.sportsmatch.service;

import com.swengineer.sportsmatch.dto.UserDTO;
import com.swengineer.sportsmatch.entity.TeamEntity;
import com.swengineer.sportsmatch.entity.UserEntity;
import com.swengineer.sportsmatch.repository.TeamRepository;
import com.swengineer.sportsmatch.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private TeamRepository teamRepository; // TeamRepository 주입

    @Autowired
    private UserRepository userRepository;

    // 회원가입
    public UserDTO register(UserDTO userDTO) {
        // 닉네임 중복 확인
        if (userRepository.findByNickname(userDTO.getNickname()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "이미 사용 중인 닉네임입니다.");
        }

        // 팀 ID가 없는 경우 null로 처리
        TeamEntity teamEntity = null;

        // UserDTO -> UserEntity 변환 후 저장
        UserEntity userEntity = UserEntity.toSaveEntity(userDTO, teamEntity);
        UserEntity savedEntity = userRepository.save(userEntity);

        // 저장된 UserEntity를 UserDTO로 변환 후 반환
        return UserDTO.toUserDTO(savedEntity);
    }




    // 로그인
    public UserDTO login(String nickname, String passwd) {
        // 닉네임으로 사용자 조회
        Optional<UserEntity> userEntityOptional = userRepository.findByNickname(nickname);
        if (userEntityOptional.isPresent()) {
            UserEntity userEntity = userEntityOptional.get();
            // 비밀번호 검증
            if (userEntity.getPasswd().equals(passwd)) {
                return UserDTO.toUserDTO(userEntity);
            } else {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 닉네임을 가진 사용자를 찾을 수 없습니다.");
        }
    }
}

