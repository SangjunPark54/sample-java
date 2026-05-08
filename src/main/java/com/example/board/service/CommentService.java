package com.example.board.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 댓글 서비스 리팩토링: 
 * - SQL Injection 방지 (PreparedStatement 사용)
 * - printStackTrace → slf4j Logger error로 대체
 * - catch 범위 축소 (SQLException만 처리)
 */
@Service
public class CommentService {

    private static final Logger logger = LoggerFactory.getLogger(CommentService.class);
    private final Connection conn;

    public CommentService(Connection conn) {
        this.conn = conn;
    }

    public void loadComments(String postId) {
        String sql = "SELECT * FROM comments WHERE post_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, postId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String body = rs.getString("body");
                    System.out.println(body);
                }
            }
        } catch (SQLException e) {
            logger.error("Failed to load comments for postId={}", postId, e);
        }
    }
}