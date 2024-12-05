package com.swengineer.sportsmatch.entity;

import com.swengineer.sportsmatch.dto.UserDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "user")
public class UserEntity {

    @EmbeddedId
    private UserId id; // 복합 키

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id", insertable = false, updatable = false) // 외래 키 매핑
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
    private LocalDateTime registDate; // 가입 날짜

    @Column(nullable = false)
    private boolean banYn = false; // 차단 여부

    @Column(length = 255)
    private String location; // 위치

    @Column(nullable = false)
    private int age; // 나이

    @Column(nullable = false, length = 1)
    private char sex; // 성별

    @Column(length = 255)
    private String position; // 포지션

    // 복합 키 클래스 정의
    @Embeddable
    public static class UserId implements Serializable {
        @Column(name = "user_id")
        private int userId;

        @Column(name = "team_id")
        private int teamId;

        // 기본 생성자
        public UserId() {}

        // 생성자
        public UserId(int userId, int teamId) {
            this.userId = userId;
            this.teamId = teamId;
        }

        // equals, hashCode 구현
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            UserId userId1 = (UserId) o;
            return userId == userId1.userId && teamId == userId1.teamId;
        }

        @Override
        public int hashCode() {
            return Objects.hash(userId, teamId);
        }

        public int getUserId() {
            return userId;
        }

        public int getTeamId() {
            return teamId;
        }
    }

    // 위임 메서드 추가
    public int getUserId() {
        return id != null ? id.getUserId() : 0;
    }

    public int getTeamId() {
        return id != null ? id.getTeamId() : 0;
    }

    // UserEntity 생성 메서드 추가
    public static UserEntity toSaveEntity(UserDTO userDTO, TeamEntity teamEntity) {
        UserEntity userEntity = new UserEntity();

        // 복합 키 설정
        UserId userId = new UserId(userDTO.getUser_id(), userDTO.getTeam_id());
        userEntity.setId(userId);

        // 연관 관계 설정
        userEntity.setTeam(teamEntity);

        // 기타 속성 설정
        userEntity.setPasswd(userDTO.getPasswd());
        userEntity.setUserName(userDTO.getUser_name());
        userEntity.setPhoneNumber(userDTO.getPhone_number());
        userEntity.setNickname(userDTO.getNickname());
        userEntity.setRegistDate(userDTO.getRegistDate());
        userEntity.setBanYn(userDTO.isBan_yn());
        userEntity.setLocation(userDTO.getLocation());
        userEntity.setAge(userDTO.getAge());
        userEntity.setSex(userDTO.getSex());
        userEntity.setPosition(userDTO.getPosition());

        return userEntity;
    }
}


