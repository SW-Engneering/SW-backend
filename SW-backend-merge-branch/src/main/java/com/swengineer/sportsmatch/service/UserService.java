package com.swengineer.sportsmatch.service;

import com.swengineer.sportsmatch.dto.UserDTO;
import com.swengineer.sportsmatch.entity.TeamEntity;
import com.swengineer.sportsmatch.entity.UserEntity;
import com.swengineer.sportsmatch.repository.TeamMemberRepository;
import com.swengineer.sportsmatch.repository.TeamRepository;
import com.swengineer.sportsmatch.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder; // 비밀번호 암호화 검증
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final TeamRepository teamRepository;
    private final TeamMemberRepository teamMemberRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,
                       TeamRepository teamRepository,
                       TeamMemberRepository teamMemberRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.teamRepository = teamRepository;
        this.teamMemberRepository = teamMemberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // 회원가입
    public UserDTO register(UserDTO userDTO) {
        if (userRepository.findByNickname(userDTO.getNickname()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "이미 사용 중인 닉네임입니다.");
        }

        TeamEntity teamEntity = null;
        userDTO.setPasswd(passwordEncoder.encode(userDTO.getPasswd())); // 비밀번호암호화
        UserEntity userEntity = UserEntity.toSaveEntity(userDTO, teamEntity);
        UserEntity savedEntity = userRepository.save(userEntity);

        return UserDTO.toUserDTO(savedEntity);
    }

    // 로그인
    public UserDTO login(String nickname, String passwd) {
        Optional<UserEntity> userEntityOptional = userRepository.findByNickname(nickname);
        if (userEntityOptional.isPresent()) {
            UserEntity userEntity = userEntityOptional.get();
            if (passwordEncoder.matches(passwd, userEntity.getPasswd())){
                return UserDTO.toUserDTO(userEntity);
            } else {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 닉네임을 가진 사용자를 찾을 수 없습니다.");
        }
    }

    // 비밀번호 검증 로직
    public boolean validatePassword(int userId, String rawPassword) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "유저를 찾을 수 없습니다."));

        return passwordEncoder.matches(rawPassword, user.getPasswd());
    }

    // 회원탈퇴 로직
    public void deleteUser(int userId, String rawPassword) {
        // 비밀번호 검증
        if (!validatePassword(userId, rawPassword)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }

        // 유저 조회
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "유저를 찾을 수 없습니다."));

        // 유저가 팀에 속한 경우 처리
        if (user.getTeam() != null) {
            TeamEntity team = user.getTeam();

            // 유저가 팀 리더인 경우 예외 처리
            if (team.getLeader().getUserId() == userId) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "팀 리더는 회원 탈퇴를 할 수 없습니다. 팀장을 양도하거나 팀 삭제를 먼저 해주세요.");
            }

            // 팀 멤버 관계 삭제
            teamMemberRepository.deleteByUser_UserId(userId);
        }

        // 유저 삭제
        userRepository.deleteById(userId);
    }
    //사용자 정보 조회
    public UserDTO getUserById(int userId) {
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "유저를 찾을 수 없습니다."));
        return UserDTO.toUserDTO(userEntity);
    }
    //사용자 목록 조회
    public Iterable<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(UserDTO::toUserDTO)
                .collect(Collectors.toList());
    }
    // 사용자 정보 수정 로직 추가
    public UserDTO updateUser(int userId, UserDTO userDTO) {
        // 비밀번호 확인: 사용자가 입력한 비밀번호가 데이터베이스의 비밀번호와 일치하는지 검증
        if (!validatePassword(userId, userDTO.getPasswd())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }

        // 닉네임 중복 확인: 입력한 닉네임이 이미 사용 중인지 확인
        if (userRepository.findByNickname(userDTO.getNickname()).isPresent() &&
                !userRepository.findById(userId).get().getNickname().equals(userDTO.getNickname())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "이미 사용 중인 닉네임입니다.");
        }

        // 유저 정보 수정: 사용자 ID로 유저 정보를 조회
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "유저를 찾을 수 없습니다."));

        // 수정할 정보 설정
        userEntity.setNickname(userDTO.getNickname()); // 닉네임 수정
        userEntity.setPasswd(passwordEncoder.encode(userDTO.getPasswd())); // 비밀번호 암호화 후 수정
        userEntity.setLocation(userDTO.getLocation()); // 위치 수정
        userEntity.setPosition(userDTO.getPosition()); // 포지션 수정
        userEntity.setPhoneNumber(userDTO.getPhone_number()); // 전화번호 수정

        // 수정된 유저 정보를 저장하고 반환
        UserEntity updatedEntity = userRepository.save(userEntity);
        return UserDTO.toUserDTO(updatedEntity); // DTO로 변환하여 반환
    }
}
