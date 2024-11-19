package com.swengineer.sportsmatch.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String postContent;

    private String postImagePath;

    private LocalDateTime postCreatedTime = LocalDateTime.now();

    @Column(nullable = false)
    private LocalDateTime postUpdatedTime = LocalDateTime.now();

    private Integer postHits = 0;
    private Integer postLikeCount = 0;
    private Integer postDislikeCount = 0;
    private Integer postReportCount = 0;
    private Integer postCommentCount = 0;

    @Column(nullable = false)
    private Integer userId;

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
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

    public Integer getPostHits() {
        return postHits;
    }

    public void setPostHits(Integer postHits) {
        this.postHits = postHits;
    }

    public Integer getPostLikeCount() {
        return postLikeCount;
    }

    public void setPostLikeCount(Integer postLikeCount) {
        this.postLikeCount = postLikeCount;
    }

    public Integer getPostDislikeCount() {
        return postDislikeCount;
    }

    public void setPostDislikeCount(Integer postDislikeCount) {
        this.postDislikeCount = postDislikeCount;
    }

    public Integer getPostReportCount() {
        return postReportCount;
    }

    public void setPostReportCount(Integer postReportCount) {
        this.postReportCount = postReportCount;
    }

    public Integer getPostCommentCount() {
        return postCommentCount;
    }

    public void setPostCommentCount(Integer postCommentCount) {
        this.postCommentCount = postCommentCount;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
