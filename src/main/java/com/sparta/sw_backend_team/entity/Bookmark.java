package com.sparta.sw_backend_team.entity;

import jakarta.persistence.*;

@Entity
public class Bookmark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookmarkId;

    @Column(nullable = false)
    private int userId;

    // 기본 생성자
    public Bookmark() {
    }

    // 모든 필드를 포함한 생성자
    public Bookmark(int userId) {
        this.userId = userId;
    }

    // Getter와 Setter
    public int getBookmarkId() {
        return bookmarkId;
    }

    public void setBookmarkId(int bookmarkId) {
        this.bookmarkId = bookmarkId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}

