package com.sparta.sw_backend_team.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int postId;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String postContent;

    @Column(length = 255)
    private String postImagePath;

    @Column(nullable = true, updatable = false)
    private LocalDateTime postCreatedTime;

    @Column(nullable = true)
    private LocalDateTime postUpdatedTime;

    @Column(nullable = true, columnDefinition = "INT DEFAULT 0")
    private int postHits;

    @Column(nullable = true, columnDefinition = "INT DEFAULT 0")
    private int postLikeCount;

    @Column(nullable = true, columnDefinition = "INT DEFAULT 0")
    private int postDislikeCount;

    @Column(nullable = true, columnDefinition = "INT DEFAULT 0")
    private int postReportCount;

    @Column(nullable = true, columnDefinition = "INT DEFAULT 0")
    private int postCommentCount;

    @Column(nullable = false)
    private int userId;

    // 기본 생성자
    public Post() {
    }

    // 모든 필드를 포함한 생성자
    public Post(String postContent, String postImagePath, LocalDateTime postCreatedTime, LocalDateTime postUpdatedTime,
                int postHits, int postLikeCount, int postDislikeCount, int postReportCount, int postCommentCount, int userId) {
        this.postContent = postContent;
        this.postImagePath = postImagePath;
        this.postCreatedTime = postCreatedTime;
        this.postUpdatedTime = postUpdatedTime;
        this.postHits = postHits;
        this.postLikeCount = postLikeCount;
        this.postDislikeCount = postDislikeCount;
        this.postReportCount = postReportCount;
        this.postCommentCount = postCommentCount;
        this.userId = userId;
    }

    // Getter와 Setter
    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public String getPostImagePath() {
        return postImagePath;
    }

    public void setPostImagePath(String postImagePath) {
        this.postImagePath = postImagePath;
    }

    public LocalDateTime getPostCreatedTime() {
        return postCreatedTime;
    }

    public void setPostCreatedTime(LocalDateTime postCreatedTime) {
        this.postCreatedTime = postCreatedTime;
    }

    public LocalDateTime getPostUpdatedTime() {
        return postUpdatedTime;
    }

    public void setPostUpdatedTime(LocalDateTime postUpdatedTime) {
        this.postUpdatedTime = postUpdatedTime;
    }

    public int getPostHits() {
        return postHits;
    }

    public void setPostHits(int postHits) {
        this.postHits = postHits;
    }

    public int getPostLikeCount() {
        return postLikeCount;
    }

    public void setPostLikeCount(int postLikeCount) {
        this.postLikeCount = postLikeCount;
    }

    public int getPostDislikeCount() {
        return postDislikeCount;
    }

    public void setPostDislikeCount(int postDislikeCount) {
        this.postDislikeCount = postDislikeCount;
    }

    public int getPostReportCount() {
        return postReportCount;
    }

    public void setPostReportCount(int postReportCount) {
        this.postReportCount = postReportCount;
    }

    public int getPostCommentCount() {
        return postCommentCount;
    }

    public void setPostCommentCount(int postCommentCount) {
        this.postCommentCount = postCommentCount;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
