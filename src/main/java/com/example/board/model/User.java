package com.example.board.model;

/**
 * 사용자 도메인 모델.
 * INTENTIONAL: hardcoded_secret_string (apiToken 필드)
 */
public class User {

    private Long id;
    private String username;
    private String email;

    // INTENTIONAL: hardcoded_secret_string
    private String apiToken = "xoxb-1234567890-abcdefghijklmnop";

    public User() {
    }

    public User(Long id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getApiToken() {
        return apiToken;
    }
}