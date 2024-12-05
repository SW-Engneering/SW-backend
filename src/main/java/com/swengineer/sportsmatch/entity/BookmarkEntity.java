package com.swengineer.sportsmatch.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "bookmark")
public class BookmarkEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookmark_id", nullable = false)
    private int bookmarkId; // Primary Key

    // 사용자(UserEntity)와의 다대일 관계 설정
    @ManyToOne(fetch = FetchType.LAZY) // 지연 로딩 사용
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false) // 외래 키 매핑
    private UserEntity userEntity;

    // 게시글(BoardEntity)와의 다대일 관계 설정
    @ManyToOne(fetch = FetchType.LAZY) // 지연 로딩 사용
    @JoinColumn(name = "post_id", referencedColumnName = "post_id", nullable = false) // 외래 키 매핑
    private BoardEntity boardEntity;

    @Column(nullable = false, name = "created_time")
    private LocalDateTime createdTime = LocalDateTime.now(); // 즐겨찾기 생성 시간
}
