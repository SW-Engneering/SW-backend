package com.swengineer.sportsmatch.dto;

import com.swengineer.sportsmatch.entity.CommentEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.w3c.dom.Text;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
    private int comment_id; // 댓글 ID
    private int post_id; // 게시글 ID
    private int user_id; // 사용자 ID
    private String comment_content; // 댓글 내용
    private boolean comment_delete_yn; // 삭제 여부
    private LocalDateTime comment_insert_time; // 작성 시간
    private LocalDateTime comment_updated_time; // 수정 시간
    private boolean comment_secret_yn; // 비밀 댓글 여부
    private boolean comment_hidden_yn; // 숨김 여부

    // Entity → DTO 변환 메서드
    public static CommentDTO toCommentDTO(CommentEntity commentEntity, int post_id, int user_id) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setComment_id(commentEntity.getComment_id());
        commentDTO.setPost_id(post_id);
        commentDTO.setUser_id(user_id);
        commentDTO.setComment_content(commentEntity.getComment_content());
        commentDTO.setComment_insert_time(commentEntity.getComment_insert_time());
        commentDTO.setComment_updated_time(commentEntity.getComment_updated_time());
        commentDTO.setComment_secret_yn(commentEntity.isComment_secret_yn());
        commentDTO.setComment_hidden_yn(commentEntity.isComment_hidden_yn());
        return commentDTO;
    }
}
