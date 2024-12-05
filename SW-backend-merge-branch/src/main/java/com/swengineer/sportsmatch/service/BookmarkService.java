package com.swengineer.sportsmatch.service;

import com.swengineer.sportsmatch.dto.BoardDTO;
import com.swengineer.sportsmatch.entity.BookmarkEntity;
import com.swengineer.sportsmatch.entity.BoardEntity;
import com.swengineer.sportsmatch.entity.UserEntity;
import com.swengineer.sportsmatch.repository.BookmarkRepository;
import com.swengineer.sportsmatch.repository.BoardRepository;
import com.swengineer.sportsmatch.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class BookmarkService {

    @Autowired
    private BookmarkRepository bookmarkRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BoardRepository boardRepository;

    // 즐겨찾기 생성
    public void addBookmark(int userId, int postId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다."));
        BoardEntity board = boardRepository.findById(postId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "게시글을 찾을 수 없습니다."));

        // 중복 확인
        if (!bookmarkRepository.findByUserIdAndPostId(userId, postId).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "이미 즐겨찾기한 게시글입니다.");
        }

        // 즐겨찾기 생성
        BookmarkEntity bookmark = new BookmarkEntity();
        bookmark.setUserEntity(user);
        bookmark.setBoardEntity(board);
        bookmarkRepository.save(bookmark);
    }

    // 즐겨찾기 삭제
    public void removeBookmark(int userId, int postId) {
        List<BookmarkEntity> bookmarks = bookmarkRepository.findByUserIdAndPostId(userId, postId);
        if (bookmarks.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "즐겨찾기를 찾을 수 없습니다.");
        }

        bookmarkRepository.deleteAll(bookmarks); // 해당 즐겨찾기 삭제
    }

    // 즐겨찾기 목록 조회
    public List<BoardDTO> getBookmarks(int userId) {
        List<BookmarkEntity> bookmarks = bookmarkRepository.findBookmarksByUserIdOrderByCreatedTimeDesc(userId);

        // BookmarkEntity -> BoardDTO 변환
        return bookmarks.stream()
                .map(bookmark -> {
                    BoardEntity board = bookmark.getBoardEntity();
                    return BoardDTO.toBoardDTO(board, userId); // BoardDTO 변환
                })
                .toList();
    }
}
