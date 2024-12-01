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
public class BoardEntity extends BaseEntity {

    @Id //pk 컬럼 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int post_id; // 게시글 id

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity; // 사용자 ID

    @Column(nullable = false, name = "post_type")
    private String post_type;  // 게시판 타입 (팀 게시판: "team", 팀원 게시판: "member")

    @Column(nullable = false, length = 50)
    private String post_writer; // 작성자

    @Column(nullable = false, length = 100)
    private String post_title; // 제목

    @Column(nullable = false, length = 1000)
    private String post_content; // 내용

    //@Column(nullable = true, length = 255)
    //private String post_imagePath; // 이미지 파일 경로

    @Column(nullable = false)
    private int post_hits = 0; // 조회수 (기본값 0)

    @Column(nullable = false)
    private int post_like_count = 0; // 추천수 (기본값 0)

    @Column(nullable = false)
    private int post_dislike_count = 0; // 비추천수 (기본값 0)

    @Column(nullable = false)
    private int post_report_count = 0; // 신고 카운트 (기본값 0)

    @Column(nullable = false)
    private int post_comment_count = 0; // 댓글 개수 (기본값 0)

    @OneToMany(mappedBy = "boardEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<CommentEntity> board_commentEntityList = new ArrayList<>();


    public static  BoardEntity toSaveEntity(BoardDTO boardDTO, UserEntity userEntity, String postType){
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
        boardEntity.setUserEntity(userEntity);
        return boardEntity;
    }
}
