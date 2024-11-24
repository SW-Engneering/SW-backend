package com.swengineer.sportsmatch.service;

import com.swengineer.sportsmatch.dto.CommentDTO;
import com.swengineer.sportsmatch.entity.BoardEntity;
import com.swengineer.sportsmatch.entity.CommentEntity;
import com.swengineer.sportsmatch.entity.UserEntity;
import com.swengineer.sportsmatch.repository.BoardRepository;
import com.swengineer.sportsmatch.repository.CommentRepository;
import com.swengineer.sportsmatch.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private UserRepository userRepository;

    // 댓글 생성
    public CommentDTO createComment(CommentDTO commentDTO, int postId, int userId) {
        Optional<BoardEntity> boardEntityOptional = boardRepository.findById(postId);
        Optional<UserEntity> userEntityOptional = userRepository.findById(userId);

        if (boardEntityOptional.isPresent() && userEntityOptional.isPresent()) {
            CommentEntity commentEntity = CommentEntity.toSaveEntity(
                    commentDTO,
                    boardEntityOptional.get(),
                    userEntityOptional.get()
            );

            // 댓글 저장
            commentRepository.save(commentEntity);

            // 게시글의 댓글 수 증가
            BoardEntity boardEntity = boardEntityOptional.get();
            boardEntity.setPost_comment_count(boardEntity.getPost_comment_count() + 1);
            boardRepository.save(boardEntity);

            return CommentDTO.toCommentDTO(commentEntity, postId, userId);
        } else {
            throw new IllegalArgumentException("게시글 또는 유저 정보를 찾을 수 없습니다.");
        }
    }

    // 댓글 조회 (게시글 ID 기준)
    public List<CommentDTO> getCommentsByPost(int postId) {
        Optional<BoardEntity> boardEntityOptional = boardRepository.findById(postId);

        if (boardEntityOptional.isPresent()) {
            List<CommentEntity> commentEntities = commentRepository.findByBoardEntity(boardEntityOptional.get());
            return commentEntities.stream()
                    .map(comment -> CommentDTO.toCommentDTO(
                            comment,
                            postId,
                            comment.getUserEntity().getUser_id()
                    ))
                    .toList();
        } else {
            throw new IllegalArgumentException("해당 게시글을 찾을 수 없습니다.");
        }
    }

    // 댓글 수정
    public CommentDTO updateComment(int commentId, String updatedContent) {
        Optional<CommentEntity> commentEntityOptional = commentRepository.findById(commentId);

        if (commentEntityOptional.isPresent()) {
            CommentEntity commentEntity = commentEntityOptional.get();
            commentEntity.setComment_content(updatedContent);
            commentEntity.setComment_updated_time(LocalDateTime.now());
            commentRepository.save(commentEntity);

            return CommentDTO.toCommentDTO(
                    commentEntity,
                    commentEntity.getBoardEntity().getPost_id(),
                    commentEntity.getUserEntity().getUser_id()
            );
        } else {
            throw new IllegalArgumentException("댓글을 찾을 수 없습니다.");
        }
    }

    // 댓글 삭제
    @Transactional
    public void deleteComment(int commentId) {
        // 댓글 조회
        CommentEntity commentEntity = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("댓글을 찾을 수 없습니다."));

        // 댓글 삭제 여부 및 삭제 시간 설정
        commentEntity.setComment_delete_yn(true);
        commentEntity.setComment_deleted_time(LocalDateTime.now());
        commentRepository.save(commentEntity);

        // Lazy 로딩 문제를 피하기 위해 BoardEntity 초기화
        BoardEntity boardEntity = commentEntity.getBoardEntity();
        if (boardEntity != null) {
            boardEntity.setPost_comment_count(boardEntity.getPost_comment_count() - 1);
            boardRepository.save(boardEntity);
        }
    }
}