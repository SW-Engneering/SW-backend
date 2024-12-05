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
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동 증가
    @Column(name = "inquiry_id", nullable = false)
    private Long inquiryId; // 문의 ID (Primary Key)

    @ManyToOne(fetch = FetchType.LAZY) // 사용자와 다대일 관계
    @JoinColumn(name = "user_id", nullable = false) // 외래 키
    private UserEntity userEntity; // 문의 작성자

    @Column(name = "inquiry_title", nullable = false, length = 100)
    private String inquiryTitle; // 문의 제목

    @Column(name = "inquiry_content", nullable = false, columnDefinition = "TEXT")
    private String inquiryContent; // 문의 내용

    @Column(name = "response_exists", nullable = false)
    private boolean responseExists = false; // 답변 여부

    @Column(name = "response_result", columnDefinition = "TEXT")
    private String responseResult; // 답변 내용 (없을 수도 있음)

    @Column(name = "created_at", nullable = false, updatable = false)
    private java.time.LocalDateTime createdAt = java.time.LocalDateTime.now(); // 문의 생성 시간
}
