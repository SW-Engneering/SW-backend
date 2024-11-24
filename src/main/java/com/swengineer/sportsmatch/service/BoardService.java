package com.swengineer.sportsmatch.service;

import com.swengineer.sportsmatch.dto.BoardDTO;
import com.swengineer.sportsmatch.entity.BoardEntity;
import com.swengineer.sportsmatch.entity.UserEntity;
import com.swengineer.sportsmatch.repository.BoardRepository;
import com.swengineer.sportsmatch.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        }
        return null;
    }

    // 게시글 목록 조회
    public List<BoardDTO> getBoardPosts(String postType) {
        List<BoardEntity> boardEntities = boardRepository.findByPost_type(postType);
        return boardEntities.stream()
                .map(boardEntity -> BoardDTO.toBoardDTO(boardEntity, boardEntity.getUserEntity().getUser_id()))
                .toList();
    }

    // 게시글 상세 조회
    public BoardDTO getBoardPost(int postId) {
        Optional<BoardEntity> boardEntityOptional = boardRepository.findById(postId);
        if (boardEntityOptional.isPresent()) {
            BoardEntity boardEntity = boardEntityOptional.get();

            // 조회수 증가
            boardEntity.setPost_hits(boardEntity.getPost_hits() + 1);
            boardRepository.save(boardEntity); // 변경된 조회수를 저장

            return BoardDTO.toBoardDTO(boardEntity, boardEntity.getUserEntity().getUser_id());
        }
        return null; // 게시글이 없을 경우 null 반환
    }
    // 게시글 수정
    public BoardDTO updateBoardPost(int postId, BoardDTO boardDTO) {
        Optional<BoardEntity> boardEntityOpt = boardRepository.findById(postId);
        if (boardEntityOpt.isPresent()) {
            BoardEntity boardEntity = boardEntityOpt.get();
            boardEntity.setPost_title(boardDTO.getPost_title());
            boardEntity.setPost_content(boardDTO.getPost_content());
            boardRepository.save(boardEntity);
            return BoardDTO.toBoardDTO(boardEntity, boardEntity.getUserEntity().getUser_id());
        }
        return null;// 게시글이 없을 경우 null 반환
    }

    // 게시글 삭제
    public void deleteBoardPost(int postId) {
        boardRepository.deleteById(postId);
    }
}
