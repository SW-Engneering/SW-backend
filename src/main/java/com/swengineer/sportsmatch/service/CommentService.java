package com.swengineer.sportsmatch.service;

import com.swengineer.sportsmatch.dto.CommentDTO;
import com.swengineer.sportsmatch.entity.BoardEntity;
import com.swengineer.sportsmatch.entity.CommentEntity;
import com.swengineer.sportsmatch.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.swengineer.sportsmatch.repository.BoardRepository;
import com.swengineer.sportsmatch.repository.CommentRepository;
import com.swengineer.sportsmatch.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private UserRepository userRepository;

    // 댓글 작성
    public CommentDTO createComment(CommentDTO commentDTO, int postId, int userId) {
        Optional<BoardEntity> boardEntityOptional = boardRepository.findById(postId);
        Optional<UserEntity> userEntityOptional = userRepository.findById(userId);

        if (boardEntityOptional.isPresent() && userEntityOptional.isPresent()) {
            // 댓글 저장
            BoardEntity boardEntity = boardEntityOptional.get();
            UserEntity userEntity = userEntityOptional.get();

            CommentEntity commentEntity = CommentEntity.toSaveEntity(commentDTO, boardEntity, userEntity);
            commentRepository.save(commentEntity);

            // 게시글의 댓글 수 증가
            boardEntity.setPost_comment_count(boardEntity.getPost_comment_count() + 1);
            boardRepository.save(boardEntity);

            return CommentDTO.toCommentDTO(commentEntity, postId, userId);
        } else {
            if (boardEntityOptional.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Board post with id " + postId + " not found");
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id " + userId + " not found");
            }
        }
    }

    // 댓글 목록 조회 (게시글 ID 기준)
    public List<CommentDTO> getCommentsByPost(int postId) {
        Optional<BoardEntity> boardEntityOptional = boardRepository.findById(postId);
        if (boardEntityOptional.isPresent()) {
            BoardEntity boardEntity = boardEntityOptional.get();
            List<CommentEntity> commentEntities = commentRepository.findByBoardEntity(boardEntity);
            return commentEntities.stream()
                    .map(comment -> CommentDTO.toCommentDTO(
                            comment,
                            postId,
                            comment.getUserEntity().getUser_id()
                    ))
                    .toList();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Board post with id " + postId + " not found");
        }
    }

    // 댓글 상세 조회 (댓글 ID 기준)
    public CommentDTO getCommentById(int commentId) {
        Optional<CommentEntity> commentEntityOptional = commentRepository.findById(commentId);
        if (commentEntityOptional.isPresent()) {
            CommentEntity commentEntity = commentEntityOptional.get();
            return CommentDTO.toCommentDTO(
                    commentEntity,
                    commentEntity.getBoardEntity().getPost_id(),
                    commentEntity.getUserEntity().getUser_id()
            );
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Comment with id " + commentId + " not found");
        }
    }

    // 댓글 수정
    public CommentDTO updateComment(int commentId, String updatedContent, int userId) {
        Optional<CommentEntity> commentEntityOptional = commentRepository.findById(commentId);

        if (commentEntityOptional.isPresent()) {
            CommentEntity commentEntity = commentEntityOptional.get();

            // 작성자 검증
            if (commentEntity.getUserEntity().getUser_id() != userId) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "댓글 수정 권한이 없습니다.");
            }

            // 댓글 내용 수정
            commentEntity.setComment_content(updatedContent);
            commentRepository.save(commentEntity);

            return CommentDTO.toCommentDTO(
                    commentEntity,
                    commentEntity.getBoardEntity().getPost_id(),
                    commentEntity.getUserEntity().getUser_id()
            );
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Comment with id " + commentId + " not found");
        }
    }

    // 댓글 삭제
    public void deleteComment(int commentId, int userId) {
        Optional<CommentEntity> commentEntityOptional = commentRepository.findById(commentId);

        if (commentEntityOptional.isPresent()) {
            CommentEntity commentEntity = commentEntityOptional.get();

            // 작성자 검증
            if (commentEntity.getUserEntity().getUser_id() != userId) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "댓글 삭제 권한이 없습니다.");
            }

            // 게시글의 댓글 수 감소
            BoardEntity boardEntity = commentEntity.getBoardEntity();
            if (boardEntity.getPost_comment_count() > 0) {
                boardEntity.setPost_comment_count(boardEntity.getPost_comment_count() - 1);
                boardRepository.save(boardEntity);
            }

            // 댓글 삭제
            commentRepository.deleteById(commentId);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Comment with id " + commentId + " not found");
        }
    }

}
