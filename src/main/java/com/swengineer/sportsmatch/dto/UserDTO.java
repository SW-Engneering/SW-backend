package com.swengineer.sportsmatch.dto;

import com.swengineer.sportsmatch.entity.TeamEntity;
import com.swengineer.sportsmatch.entity.UserEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor // 기본 생성자
@AllArgsConstructor // 모든 필드를 매개변수로 하는 생성자
public class UserDTO {
    private int user_id;
    private Integer team_id; // 팀 ID를 nullable로 설정 (팀 미소속 가능)
    private String passwd;
    private String user_name;
    private String phone_number;
    private String nickname;
    private LocalDateTime registDate;
    private boolean ban_yn;
    private String location;
    private int age;
    private char sex; // 1자 문자로 표현
    private String position;
    //private String profile_image;

    // 엔티티를 DTO로 변환하는 메서드
    public static UserDTO toUserDTO(UserEntity userEntity) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUser_id(userEntity.getUserId());
        // 팀 정보가 있는 경우 team_id 설정
        userDTO.setTeam_id(userEntity.getTeam() != null ? userEntity.getTeam().getTeamId() : null);
        userDTO.setPasswd(userEntity.getPasswd());
        userDTO.setUser_name(userEntity.getUserName());
        userDTO.setPhone_number(userEntity.getPhoneNumber());
        userDTO.setNickname(userEntity.getNickname());
        userDTO.setRegistDate(userEntity.getRegistDate());
        userDTO.setBan_yn(userEntity.isBanYn());
        userDTO.setLocation(userEntity.getLocation());
        userDTO.setAge(userEntity.getAge());
        userDTO.setSex(userEntity.getSex());
        userDTO.setPosition(userEntity.getPosition());
        //userDTO.setProfile_image(userEntity.getProfile_image());
        return userDTO;
    }
}
