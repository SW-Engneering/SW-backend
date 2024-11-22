package com.swengineer.sportsmatch.entity;

import com.swengineer.sportsmatch.dto.UserDTO;
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
public class UserEntity extends BaseEntity {

    @Id // Primary Key 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_id; // 사용자 ID

    @Column(nullable = false)
    private int team_id; // 팀 ID

    @Column(nullable = false, length = 50)
    private String passwd; // 비밀번호

    @Column(nullable = false, length = 50)
    private String user_name; // 사용자 이름

    @Column(nullable = false, length = 15)
    private String phone_number; // 전화번호

    @Column(nullable = false, length = 50)
    private String nickname; // 닉네임

    @Column(nullable = false)
    private LocalDateTime registDate; // 가입일자

    @Column(nullable = false)
    private byte ban_yn; // 차단 여부 (1 = 차단, 0 = 차단 아님)

    @Column(nullable = true, length = 255)
    private String location; // 위치

    @Column(nullable = false)
    private int age; // 나이

    @Column(nullable = false, length = 1)
    private char sex; // 성별 (M: 남성, F: 여성)

    @Column(nullable = true, length = 255)
    private String position; // 포지션

    //@Column(nullable = true, length = 255)
    //private String profile_image; // 프로필 이미지 경로

    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<CommentEntity> user_postEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<CommentEntity> user_commentEntityList = new ArrayList<>();

    // UserDTO로 변환하는 메서드
    public static UserEntity toSaveEntity(UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setTeam_id(userDTO.getTeam_id());
        userEntity.setPasswd(userDTO.getPasswd());
        userEntity.setUser_name(userDTO.getUser_name());
        userEntity.setPhone_number(userDTO.getPhone_number());
        userEntity.setNickname(userDTO.getNickname());
        userEntity.setRegistDate(userDTO.getRegistDate());
        userEntity.setBan_yn(userDTO.getBan_yn());
        userEntity.setLocation(userDTO.getLocation());
        userEntity.setAge(userDTO.getAge());
        userEntity.setSex(userDTO.getSex());
        userEntity.setPosition(userDTO.getPosition());
        //userEntity.setProfile_image(userDTO.getProfile_image());
        return userEntity;
    }

    // UserDTO로 업데이트하는 메서드
    public static UserEntity toUpdateEntity(UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUser_id(userDTO.getUser_id());
        userEntity.setTeam_id(userDTO.getTeam_id());
        userEntity.setPasswd(userDTO.getPasswd());
        userEntity.setUser_name(userDTO.getUser_name());
        userEntity.setPhone_number(userDTO.getPhone_number());
        userEntity.setNickname(userDTO.getNickname());
        userEntity.setRegistDate(userDTO.getRegistDate());
        userEntity.setBan_yn(userDTO.getBan_yn());
        userEntity.setLocation(userDTO.getLocation());
        userEntity.setAge(userDTO.getAge());
        userEntity.setSex(userDTO.getSex());
        userEntity.setPosition(userDTO.getPosition());
        //userEntity.setProfile_image(userDTO.getProfile_image());
        return userEntity;
    }
}
