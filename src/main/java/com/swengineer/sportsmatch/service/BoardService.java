package com.swengineer.sportsmatch.service;

import com.swengineer.sportsmatch.dto.BoardDTO;
import com.swengineer.sportsmatch.entity.BoardEntity;
import com.swengineer.sportsmatch.entity.TeamEntity;
import com.swengineer.sportsmatch.entity.UserEntity;
import com.swengineer.sportsmatch.repository.BoardRepository;
import com.swengineer.sportsmatch.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private UserRepository userRepository;

    // 게시글 작성
    public BoardDTO createBoardPost(BoardDTO boardDTO, int userId, String postType) {
        Optional<UserEntity> user = userRepository.findById(userId);
        if (user.isPresent()) {
            BoardEntity boardEntity = BoardEntity.toSaveEntity(boardDTO, user.get(), postType);
            boardRepository.save(boardEntity);
            return BoardDTO.toBoardDTO(boardEntity, userId);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id " + userId + " not found");
        }
    }

    // 게시글 목록 조회
    public List<BoardDTO> getBoardPosts(String postType) {
        List<BoardEntity> boardEntities = boardRepository.findByPost_type(postType);
        return boardEntities.stream()
                .map(boardEntity -> BoardDTO.toBoardDTO(boardEntity, boardEntity.getUserEntity().getUserId()))
                .toList();
    }

    // 게시글 상세 조회
    public BoardDTO getBoardPost(int postId) {
        Optional<BoardEntity> boardEntityOptional = boardRepository.findById(postId);
        if (boardEntityOptional.isPresent()) {
            BoardEntity boardEntity = boardEntityOptional.get();
            boardEntity.setPost_hits(boardEntity.getPost_hits() + 1);
            boardRepository.save(boardEntity);
            return BoardDTO.toBoardDTO(boardEntity, boardEntity.getUserEntity().getUserId());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Board post with id " + postId + " not found");
        }
    }

    // 게시글 수정
    public BoardDTO updateBoardPost(int postId, BoardDTO boardDTO, int userId) {
        Optional<BoardEntity> boardEntityOpt = boardRepository.findById(postId);
        if (boardEntityOpt.isPresent()) {
            BoardEntity boardEntity = boardEntityOpt.get();

            // 작성자 확인
            if (boardEntity.getUserEntity().getUserId() != userId) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "수정 권한이 없습니다.");
            }

            // 게시글 수정
            boardEntity.setPost_title(boardDTO.getPost_title());
            boardEntity.setPost_content(boardDTO.getPost_content());
            boardRepository.save(boardEntity);

            return BoardDTO.toBoardDTO(boardEntity, boardEntity.getUserEntity().getUserId());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Board post with id " + postId + " not found");
        }
    }


    // 게시글 삭제
    public void deleteBoardPost(int postId, int userId) {
        Optional<BoardEntity> boardEntityOpt = boardRepository.findById(postId);
        if (boardEntityOpt.isPresent()) {
            BoardEntity boardEntity = boardEntityOpt.get();

            // 작성자 확인
            if (boardEntity.getUserEntity().getUserId() != userId) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "삭제 권한이 없습니다.");
            }

            // 게시글 삭제
            boardRepository.deleteById(postId);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Board post with id " + postId + " not found");
        }
    }

    @Transactional
    public boolean isTeamLeader(int userId) {
        Optional<UserEntity> userOpt = userRepository.findById(userId);

        if (userOpt.isPresent()) {
            UserEntity user = userOpt.get();
            TeamEntity team = user.getTeam();
            return team != null && team.getLeader().getUserId() == userId;
        }
        return false;
    }

    // 특정 유저가 작성한 게시글 조회
    public List<BoardDTO> getBoardsByUser(int userId) {
        Optional<UserEntity> userEntityOpt = userRepository.findById(userId);

        if (userEntityOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "유저를 찾을 수 없습니다.");
        }

        List<BoardEntity> boardEntities = boardRepository.findByUserEntity_UserId(userId);
        return boardEntities.stream()
                .map(board -> BoardDTO.toBoardDTO(board, userId))
                .toList();
    }
}