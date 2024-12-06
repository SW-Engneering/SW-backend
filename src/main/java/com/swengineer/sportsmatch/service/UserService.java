package com.swengineer.sportsmatch.service;

import com.swengineer.sportsmatch.dto.UserDTO;
import com.swengineer.sportsmatch.dto.UserUpdateDTO;
import com.swengineer.sportsmatch.entity.TeamEntity;
import com.swengineer.sportsmatch.entity.UserEntity;
import com.swengineer.sportsmatch.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {

    @Autowired
    private TeamRepository teamRepository; // TeamRepository 주입

    @Autowired
    private TeamMemberRepository teamMemberRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookmarkRepository bookmarkRepository;

    @Autowired
    private InquiryRepository inquiryRepository;


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

    public void deleteUser(int userId) {
        // 유저 조회
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "유저를 찾을 수 없습니다."));

        // 유저가 팀에 속한 경우 처리
        if (user.getTeam() != null) {
            TeamEntity team = user.getTeam();

            // 유저가 팀 리더인 경우
            if (team.getLeader().getUserId() == userId) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "팀 리더는 회원 탈퇴를 할 수 없습니다. 팀장을 양도하거나 팀 삭제를 먼저 해주세요.");
            }

            // 팀 멤버 관계 삭제
            teamMemberRepository.deleteByUser_UserId(userId);
        }

        bookmarkRepository.findByUserEntity(user)
                .forEach(bookmarkRepository::delete);

        // 문의 삭제
        inquiryRepository.findByUserEntity(user)
                .forEach(inquiryRepository::delete);

        // 유저 삭제
        userRepository.deleteById(userId);
    }

    public UserDTO getUserById(int userId) {
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "유저를 찾을 수 없습니다."));
        return UserDTO.toUserDTO(userEntity);
    }

    public Iterable<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(UserDTO::toUserDTO)
                .collect(Collectors.toList());
    }

    // 사용자 정보 수정 로직 추가
    public UserDTO updateUser(int userId, UserUpdateDTO userUpdateDTO) {
        // 사용자 조회
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다."));

        // 새 비밀번호 설정
        if (userUpdateDTO.getPassword() != null) {
            userEntity.setPasswd(userUpdateDTO.getPassword()); // 실제로는 해싱 필요
        }

        // 닉네임 (user_name) 업데이트
        if (userUpdateDTO.getUser_name() != null) {
            userEntity.setUserName(userUpdateDTO.getUser_name());
        }

        // 휴대폰 번호 업데이트
        if (userUpdateDTO.getPhone_number() != null) {
            userEntity.setPhoneNumber(userUpdateDTO.getPhone_number());
        }

        // 위치 업데이트
        if (userUpdateDTO.getLocation() != null) {
            userEntity.setLocation(userUpdateDTO.getLocation());
        }

        // 포지션 업데이트
        if (userUpdateDTO.getPosition() != null) {
            userEntity.setPosition(userUpdateDTO.getPosition());
        }

        // 변경 사항 저장
        UserEntity updatedEntity = userRepository.save(userEntity);

        // UserEntity → UserDTO 변환 및 반환
        return UserDTO.toUserDTO(updatedEntity);
    }




}