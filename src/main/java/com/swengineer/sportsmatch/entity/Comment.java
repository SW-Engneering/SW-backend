package com.swengineer.sportsmatch.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentId;

    @Column(nullable = false)
    private Integer postId;

    @Column(nullable = false)
    private Integer userId;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String commentContent;

    private Boolean commentDeleteYn = false;
    private LocalDateTime commentInsertTime = LocalDateTime.now();

    @Column(nullable = false)
    private LocalDateTime commentUpdatedTime = LocalDateTime.now();

    private LocalDateTime commentDeletedTime;
    private Boolean commentSecretYn = false;
    private Boolean commentHiddenYn = false;

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public Boolean getCommentDeleteYn() {
        return commentDeleteYn;
    }

    public void setCommentDeleteYn(Boolean commentDeleteYn) {
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

    public Boolean getCommentSecretYn() {
        return commentSecretYn;
    }

    public void setCommentSecretYn(Boolean commentSecretYn) {
        this.commentSecretYn = commentSecretYn;
    }

    public Boolean getCommentHiddenYn() {
        return commentHiddenYn;
    }

    public void setCommentHiddenYn(Boolean commentHiddenYn) {
        this.commentHiddenYn = commentHiddenYn;
    }
}
