package com.swengineer.sportsmatch.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "inquiry")
public class InquiryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inquiryId;  // 문의 ID

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity userEntity;  // 문의한 사용자

    private String inquiryTitle;  // 문의 제목
    private String inquiryContent;  // 문의 내용

    private boolean responseExists;  // 답변 유무
    private String responseResult;  // 답변 결과
}
