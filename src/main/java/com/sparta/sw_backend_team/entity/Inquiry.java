package com.sparta.sw_backend_team.entity;

import jakarta.persistence.*;

@Entity
public class Inquiry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int inquiryId;

    @Column(nullable = false)
    private int userId;

    // 기본 생성자
    public Inquiry() {
    }

    // 모든 필드를 포함한 생성자
    public Inquiry(int userId) {
        this.userId = userId;
    }

    // Getter와 Setter
    public int getInquiryId() {
        return inquiryId;
    }

    public void setInquiryId(int inquiryId) {
        this.inquiryId = inquiryId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}

