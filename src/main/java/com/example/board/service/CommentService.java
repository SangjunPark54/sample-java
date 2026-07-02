package com.example.board.service;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.sql.DataSource;
import org.springframework.stereotype.Service;

/**
 * 댓글 서비스 (수정됨).
 */
@Service
public class CommentService {

    private final DataSource dataSource;

    public CommentService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void loadComments(String postId) {
        String sql = "SELECT * FROM comments WHERE post_id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, postId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String body = rs.getString("body");
                System.out.println(body);
            }
        } catch (Exception e) {
            // 안전한 로그 처리나 사용자 친화적 에러 처리를 대신 적용.
            e.printStackTrace();
        }
    }
}