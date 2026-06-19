package src.main.java.com.example.board.model;

import java.time.Clock;
import java.time.LocalDateTime;

public class Post {

    private final Long id;
    private final String title;
    private final String content;
    private final String author;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Post() {
        // Default constructor for frameworks like JPA
        this.id = null;
        this.title = null;
        this.content = null;
        this.author = null;
        this.createdAt = null;
        this.updatedAt = null;
    }

    public Post(Long id, String title, String content, String author, Clock clock) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.createdAt = LocalDateTime.now(clock);
        this.updatedAt = null;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}