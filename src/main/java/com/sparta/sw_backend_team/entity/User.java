package com.sparta.sw_backend_team.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column(nullable = true) // team_id
    private Integer teamId;

    @Column(nullable = false, length = 255) // passwd
    private String passwd;

    @Column(nullable = false, length = 50) // user_name
    private String userName;

    @Column(nullable = false, length = 15) // phone_number
    private String phoneNumber;

    @Column(nullable = false, length = 50) // nickname
    private String nickname;

    @Column(nullable = true) // registDate
    private LocalDateTime registDate;

    @Column(nullable = true) // ban_yn
    private Boolean banYn;

    @Column(nullable = true, length = 255) // location
    private String location;

    @Column(nullable = true) // age
    private Integer age;

    @Column(nullable = true, length = 1) // sex
    private String sex;

    @Column(nullable = true, length = 50) // position
    private String position;

    @Column(nullable = true, length = 255) // profile_image
    private String profileImage;

    // 기본 생성자
    public User() {
    }

    // 모든 필드를 포함한 생성자
    public User(Integer teamId, String passwd, String userName, String phoneNumber, String nickname,
                LocalDateTime registDate, Boolean banYn, String location, Integer age, String sex, String position, String profileImage) {
        this.teamId = teamId;
        this.passwd = passwd;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.nickname = nickname;
        this.registDate = registDate;
        this.banYn = banYn;
        this.location = location;
        this.age = age;
        this.sex = sex;
        this.position = position;
        this.profileImage = profileImage;
    }

    // Getter와 Setter
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public LocalDateTime getRegistDate() {
        return registDate;
    }

    public void setRegistDate(LocalDateTime registDate) {
        this.registDate = registDate;
    }

    public Boolean getBanYn() {
        return banYn;
    }

    public void setBanYn(Boolean banYn) {
        this.banYn = banYn;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }
}
