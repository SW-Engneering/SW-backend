package com.swengineer.sportsmatch.dto;

import com.swengineer.sportsmatch.entity.BoardEntity;
import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@ToString
@NoArgsConstructor //기본생성자
@AllArgsConstructor //모든 필드를 매개변수로하는 생성자
public class BoardDTO {
    private int post_id;
    private int user_id;
    private String post_type;
    private String post_writer;
    private String post_title;
    private String post_content;
    //private String post_imagePath;
    private int post_hits;
    private LocalDateTime post_created_time;
    private LocalDateTime post_updated_time;
    private int post_like_count;
    private int post_dislike_count;
    private int post_report_count;
    private int post_comment_count;

    public static BoardDTO toBoardDTO(BoardEntity boardEntity, int user_id) {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setPost_id(boardEntity.getPost_id());
        boardDTO.setUser_id(user_id);
        boardDTO.setPost_type(boardEntity.getPost_type());
        boardDTO.setPost_writer(boardEntity.getPost_writer());
        boardDTO.setPost_title(boardEntity.getPost_title());
        boardDTO.setPost_content(boardEntity.getPost_content());
        boardDTO.setPost_hits(boardEntity.getPost_hits());
        boardDTO.setPost_like_count(boardEntity.getPost_like_count());
        boardDTO.setPost_dislike_count(boardEntity.getPost_dislike_count());
        boardDTO.setPost_report_count(boardEntity.getPost_report_count());
        boardDTO.setPost_comment_count(boardEntity.getPost_comment_count());
        boardDTO.setPost_created_time(boardEntity.getPost_created_time());
        boardDTO.setPost_updated_time(boardEntity.getPost_updated_time());
        //boardDTO.setPost_imagePath(boardEntity.getPost_imagePath());
        return boardDTO;
    }
}
