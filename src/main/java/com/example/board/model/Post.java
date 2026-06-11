package com.example.board.model;

import java.time.LocalDateTime;

/**
 * 게시글 도메인 모델 (정상 코드 — 대조군).
 */
public class Post {

    private Long id;
    private String title;
    private String content;
    private String author;
    private LocalDateTime createdAt;

    public Post() {
    }

    public Post(Long id, String title, String content, String author) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.createdAt = LocalDateTime.now();
    }

    /**
     * 게시글의 고유 ID를 반환합니다.
     *
     * @return 게시글 ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 게시글의 제목을 반환합니다.
     *
     * @return 게시글 제목
     */
    public String getTitle() {
        return title;
    }

    /**
     * 게시글의 내용을 반환합니다.
     *
     * @return 게시글 내용
     */
    public String getContent() {
        return content;
    }

    /**
     * 게시글의 저자를 반환합니다.
     *
     * @return 게시글 저자
     */
    public String getAuthor() {
        return author;
    }

    /**
     * 게시글이 생성된 날짜 및 시간을 반환합니다.
     *
     * @return 게시글 생성일시
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}