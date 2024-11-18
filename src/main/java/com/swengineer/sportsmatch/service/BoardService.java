package com.swengineer.sportsmatch.service;

import com.swengineer.sportsmatch.dto.BoardDTO;
import com.swengineer.sportsmatch.entity.BoardEntity;
import com.swengineer.sportsmatch.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {
    private  final BoardRepository boardRepository;
    public void save(BoardDTO boardDTO) {
        BoardEntity boardEntity = BoardEntity.toSaveEntity(boardDTO);
        boardRepository.save(boardEntity);
    }

    public List<BoardDTO> member_findAll() {
        List<BoardEntity> boardEntityList = boardRepository.findAll();
        List<BoardDTO> boardDTOList = boardEntityList.stream()
                .filter(boardEntity -> "member".equals(boardEntity.getPost_type())) // post_type이 "member"인 게시글만 필터링
                .map(BoardDTO::toBoardDTO) // BoardEntity를 BoardDTO로 변환
                .collect(Collectors.toList()); // 필터링된 결과를 리스트로 수집
        return boardDTOList;
    }

    public List<BoardDTO> team_findAll() {
        List<BoardEntity> boardEntityList = boardRepository.findAll();
        List<BoardDTO> boardDTOList = boardEntityList.stream()
                .filter(boardEntity -> "team".equals(boardEntity.getPost_type())) // post_type이 "team"인 게시글만 필터링
                .map(BoardDTO::toBoardDTO) // BoardEntity를 BoardDTO로 변환
                .collect(Collectors.toList()); // 필터링된 결과를 리스트로 수집
        return boardDTOList;
    }
}
