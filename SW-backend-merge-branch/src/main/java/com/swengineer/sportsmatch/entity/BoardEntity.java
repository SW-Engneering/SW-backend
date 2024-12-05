package com.swengineer.sportsmatch.entity;

import com.swengineer.sportsmatch.dto.BoardDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "post")
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private int post_id; // 게시글 ID

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
            @JoinColumn(name = "team_id", referencedColumnName = "team_id")
    })
    private UserEntity user_entity; // 사용자 (복합키 연결)

    @Column(nullable = false, name = "post_type")
    private String post_type; // 게시판 타입 (예: "team", "member")

    @Column(nullable = false, length = 50, name = "post_writer")
    private String post_writer; // 작성자

    @Column(nullable = false, length = 100, name = "post_title")
    private String post_title; // 제목

    @Column(nullable = false, length = 1000, name = "post_content")
    private String post_content; // 내용

    @Column(nullable = false, name = "post_hits")
    private int post_hits = 0; // 조회수 (기본값 0)

    @Column(nullable = false, name = "post_like_count")
    private int post_like_count = 0; // 추천수 (기본값 0)

    @Column(nullable = false, name = "post_dislike_count")
    private int post_dislike_count = 0; // 비추천수 (기본값 0)

    @Column(nullable = false, name = "post_report_count")
    private int post_report_count = 0; // 신고 카운트 (기본값 0)

    @Column(nullable = false, name = "post_comment_count")
    private int post_comment_count = 0; // 댓글 개수 (기본값 0)

    @Column(name = "post_created_time")
    private LocalDateTime postCreatedTime;

    @Column(name = "post_updated_time")
    private LocalDateTime postUpdatedTime;

    @OneToMany(mappedBy = "boardEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<CommentEntity> commentEntityList = new ArrayList<>(); // 댓글 리스트

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public int getPost_hits() {
        return post_hits;
    }

    public void setPost_hits(int post_hits) {
        this.post_hits = post_hits;
    }

    public int getPost_like_count() {
        return post_like_count;
    }

    public void setPost_like_count(int post_like_count) {
        this.post_like_count = post_like_count;
    }

    public int getPost_dislike_count() {
        return post_dislike_count;
    }

    public void setPost_dislike_count(int post_dislike_count) {
        this.post_dislike_count = post_dislike_count;
    }

    public int getPost_report_count() {
        return post_report_count;
    }

    public void setPost_report_count(int post_report_count) {
        this.post_report_count = post_report_count;
    }

    public int getPost_comment_count() {
        return post_comment_count;
    }

    public void setPost_comment_count(int post_comment_count) {
        this.post_comment_count = post_comment_count;
    }

    // Entity 변환 메서드
    public static BoardEntity toSaveEntity(BoardDTO boardDTO, UserEntity userEntity, String postType) {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setPost_type(postType);
        boardEntity.setPost_writer(boardDTO.getPost_writer());
        boardEntity.setPost_title(boardDTO.getPost_title());
        boardEntity.setPost_content(boardDTO.getPost_content());
        boardEntity.setPost_hits(0);
        boardEntity.setPost_like_count(0);
        boardEntity.setPost_dislike_count(0);
        boardEntity.setPost_report_count(0);
        boardEntity.setPost_comment_count(0);
        boardEntity.setUser_entity(userEntity);
        return boardEntity;
    }
}
