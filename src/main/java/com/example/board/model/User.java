package com.example.board.model;

/**
 * 사용자 도메인 모델.
 */
public class User {

    private Long id;
    private String username;
    private String email;

    private static final String API_TOKEN = System.getenv("API_TOKEN"); // 환경변수에서 API 토큰을 가져옵니다.

    public User() {
    }

    public User(Long id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }

    /**
     * 사용자 ID를 반환합니다.
     * @return Long : 사용자 ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 사용자 이름을 반환합니다.
     * @return String : 사용자 이름
     */
    public String getUsername() {
        return username;
    }

    /**
     * 사용자 이메일을 반환합니다.
     * @return String : 사용자 이메일
     */
    public String getEmail() {
        return email;
    }

    /**
     * API 토큰을 반환합니다.
     * @return String : API 토큰
     */
    public String getApiToken() {
        return API_TOKEN;
    }
}