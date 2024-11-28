package com.swengineer.sportsmatch.dto;

import com.swengineer.sportsmatch.entity.UserEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor // 기본생성자
@AllArgsConstructor // 모든 필드를 매개변수로하는 생성자
public class UserDTO {
    private int user_id;
    private int team_id;
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
        userDTO.setUser_id(userEntity.getUser_id());
        userDTO.setTeam_id(userEntity.getTeam_id());
        userDTO.setPasswd(userEntity.getPasswd());
        userDTO.setUser_name(userEntity.getUser_name());
        userDTO.setPhone_number(userEntity.getPhone_number());
        userDTO.setNickname(userEntity.getNickname());
        userDTO.setRegistDate(userEntity.getRegistDate());
        userDTO.setBan_yn(userEntity.isBan_yn());
        userDTO.setLocation(userEntity.getLocation());
        userDTO.setAge(userEntity.getAge());
        userDTO.setSex(userEntity.getSex());
        userDTO.setPosition(userEntity.getPosition());
        //userDTO.setProfile_image(userEntity.getProfile_image());
        return userDTO;
    }
}
