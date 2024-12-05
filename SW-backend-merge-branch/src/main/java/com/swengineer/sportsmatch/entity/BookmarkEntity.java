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

    // UserEntity 복합 키 참조
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
            @JoinColumn(name = "team_id", referencedColumnName = "team_id")
    })
    private UserEntity userEntity; // 사용자

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false) // post_id 외래 키
    private BoardEntity boardEntity; // 게시글

    @Column(nullable = false, name = "created_time")
    private LocalDateTime createdTime = LocalDateTime.now(); // 즐겨찾기 생성 시간
}

