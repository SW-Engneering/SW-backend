package com.swengineer.sportsmatch.entity;

import com.swengineer.sportsmatch.dto.CommentDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "comment")
public class CommentEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id") // 데이터베이스 컬럼 이름 명시
    private int comment_id; // 댓글 ID

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false) // 게시물과의 관계
    private BoardEntity boardEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "user_id", referencedColumnName = "id.userId"),
            @JoinColumn(name = "team_id", referencedColumnName = "id.teamId")
    })
    private UserEntity userEntity;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String comment_content; // 댓글 내용

    @Column(nullable = false)
    private boolean comment_delete_yn = false; // 삭제 여부

    @Column(nullable = false)
    private LocalDateTime comment_insert_time; // 작성 시간

    @Column(nullable = true)
    private LocalDateTime comment_updated_time; // 수정 시간

    @Column(nullable = true)
    private LocalDateTime comment_deleted_time; // 삭제 시간

    @Column(nullable = false)
    private boolean comment_secret_yn = false; // 비밀 댓글 여부

    @Column(nullable = false)
    private boolean comment_hidden_yn = false; // 숨김 여부 (관리자 조치 등)

    // Entity → DTO 변환 메서드
    public static CommentEntity toSaveEntity(CommentDTO commentDTO, BoardEntity boardEntity, UserEntity userEntity) {
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setBoardEntity(boardEntity);
        commentEntity.setUserEntity(userEntity);
        commentEntity.setComment_content(commentDTO.getComment_content());
        commentEntity.setComment_insert_time(LocalDateTime.now());
        commentEntity.setComment_delete_yn(false);
        commentEntity.setComment_secret_yn(commentDTO.isComment_secret_yn());
        commentEntity.setComment_hidden_yn(false);
        return commentEntity;
    }
}

