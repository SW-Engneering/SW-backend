package com.swengineer.sportsmatch.entity;

import com.swengineer.sportsmatch.dto.UserDTO;
import com.swengineer.sportsmatch.entity.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "user") // 데이터베이스 테이블 이름과 일치시킵니다.
public class UserEntity {

    @Id // Primary Key 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id") // 데이터베이스 컬럼 이름 명시
    private int userId; // 사용자 ID

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id", nullable = true)
    private TeamEntity team;

    @Column(nullable = false, length = 50)
    private String passwd; // 비밀번호

    @Column(nullable = false, length = 50)
    private String userName; // 사용자 이름

    @Column(nullable = false, length = 15)
    private String phoneNumber; // 전화번호

    @Column(nullable = false, length = 50, unique = true)
    private String nickname; // 닉네임

    @Column(nullable = false)
    private LocalDateTime registDate; // 가입일자

    @Column(nullable = false)
    private boolean banYn = false; // 차단 여부 (1 = 차단, 0 = 차단 아님)

    @Column(length = 255)
    private String location; // 위치

    @Column(nullable = false)
    private int age; // 나이

    @Column(nullable = false, length = 1)
    private char sex; // 성별

    @Column(length = 255)
    private String position; // 포지션


    // 댓글 리스트 (사용자가 작성한 댓글)
    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<CommentEntity> commentEntityList = new ArrayList<>();


    // 게시물 리스트 (사용자가 작성한 게시물)
    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<BoardEntity> postEntityList = new ArrayList<>();

    // 즐겨찾기 리스트 (사용자가 생성한 즐겨찾기)
    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<BookmarkEntity> bookmarkEntityList = new ArrayList<>();

    // 신고 리스트 (사용자가 생성한 신고)
    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<InquiryEntity> inquiryEntities = new ArrayList<>();

    // UserEntity 생성 메서드 추가
    public static UserEntity toSaveEntity(UserDTO userDTO, TeamEntity teamEntity) {
        UserEntity userEntity = new UserEntity();
        userEntity.setTeam(teamEntity);
        userEntity.setPasswd(userDTO.getPasswd());
        userEntity.setUserName(userDTO.getUser_name());
        userEntity.setPhoneNumber(userDTO.getPhone_number());
        userEntity.setNickname(userDTO.getNickname());
        userEntity.setRegistDate(userDTO.getRegistDate());
        userEntity.setBanYn(false);
        userEntity.setLocation(userDTO.getLocation());
        userEntity.setAge(userDTO.getAge());
        userEntity.setSex(userDTO.getSex());
        userEntity.setPosition(userDTO.getPosition());
        userEntity.setRegistDate(LocalDateTime.now());
        return userEntity;
    }

    // UserDTO로 변환하는 메서드 (team이 없는 경우)
    public static UserEntity toSaveEntity(UserDTO userDTO) {
        return toSaveEntity(userDTO, null);
    }
}

