package com.sparta.sw_backend_team.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int commentId;

    @Column(nullable = false)
    private int postId;

    @Column(nullable = false)
    private int userId;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String commentContent;

    @Column(nullable = true)
    private boolean commentDeleteYn = false;

    @Column(nullable = true)
    private LocalDateTime commentInsertTime;

    @Column(nullable = true)
    private LocalDateTime commentUpdatedTime;

    @Column(nullable = true)
    private LocalDateTime commentDeletedTime;

    @Column(nullable = true)
    private boolean commentSecretYn = false;

    @Column(nullable = true)
    private boolean commentHiddenYn = false;

    // 기본 생성자
    public Comment() {
    }

    // 모든 필드를 포함한 생성자
    public Comment(int postId, int userId, String commentContent) {
        this.postId = postId;
        this.userId = userId;
        this.commentContent = commentContent;
    }

    // Getter와 Setter
    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public boolean isCommentDeleteYn() {
        return commentDeleteYn;
    }

    public void setCommentDeleteYn(boolean commentDeleteYn) {
        this.commentDeleteYn = commentDeleteYn;
    }

    public LocalDateTime getCommentInsertTime() {
        return commentInsertTime;
    }

    public void setCommentInsertTime(LocalDateTime commentInsertTime) {
        this.commentInsertTime = commentInsertTime;
    }

    public LocalDateTime getCommentUpdatedTime() {
        return commentUpdatedTime;
    }

    public void setCommentUpdatedTime(LocalDateTime commentUpdatedTime) {
        this.commentUpdatedTime = commentUpdatedTime;
    }

    public LocalDateTime getCommentDeletedTime() {
        return commentDeletedTime;
    }

    public void setCommentDeletedTime(LocalDateTime commentDeletedTime) {
        this.commentDeletedTime = commentDeletedTime;
    }

    public boolean isCommentSecretYn() {
        return commentSecretYn;
    }

    public void setCommentSecretYn(boolean commentSecretYn) {
        this.commentSecretYn = commentSecretYn;
    }

    public boolean isCommentHiddenYn() {
        return commentHiddenYn;
    }

    public void setCommentHiddenYn(boolean commentHiddenYn) {
        this.commentHiddenYn = commentHiddenYn;
    }
}
