package com.swengineer.sportsmatch.service;

import com.swengineer.sportsmatch.dto.BoardDTO;
import com.swengineer.sportsmatch.entity.BoardEntity;
import com.swengineer.sportsmatch.entity.BookmarkEntity;
import com.swengineer.sportsmatch.entity.UserEntity;
import com.swengineer.sportsmatch.repository.BoardRepository;
import com.swengineer.sportsmatch.repository.BookmarkRepository;
import com.swengineer.sportsmatch.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional
public class BookmarkService {

    @Autowired
    private BookmarkRepository bookmarkRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private UserRepository userRepository;

    // 즐겨찾기 추가
    public void addBookmark(int userId, int postId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다."));
        BoardEntity board = boardRepository.findById(postId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "게시글을 찾을 수 없습니다."));

        if (bookmarkRepository.findByUserEntityAndBoardEntity(user, board).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "이미 즐겨찾기에 추가된 게시글입니다.");
        }

        BookmarkEntity bookmark = new BookmarkEntity();
        bookmark.setUserEntity(user);
        bookmark.setBoardEntity(board);
        bookmarkRepository.save(bookmark);
    }

    // 즐겨찾기 삭제
    public void removeBookmark(int userId, int postId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다."));
        BoardEntity board = boardRepository.findById(postId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "게시글을 찾을 수 없습니다."));

        BookmarkEntity bookmark = bookmarkRepository.findByUserEntityAndBoardEntity(user, board)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "즐겨찾기를 찾을 수 없습니다."));
        bookmarkRepository.delete(bookmark);
    }

    // 즐겨찾기 목록 조회
    public List<BoardDTO> getBookmarks(int userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다."));

        return bookmarkRepository.findByUserEntity(user).stream()
                .map(bookmark -> BoardDTO.toBoardDTO(bookmark.getBoardEntity(), userId))
                .toList();
    }
}
