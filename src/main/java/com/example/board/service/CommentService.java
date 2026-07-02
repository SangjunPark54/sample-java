package com.example.board.service;
import java.sql.Statement;
import javax.sql.DataSource;
import org.springframework.stereotype.Service;

/**
 * 댓글 서비스 (INTENTIONAL VIOLATIONS).
 * - sql_injection_use_prepared_statement
 * - printstacktrace_exposure
 * - catch_generic_exception (🔁 4/6)
 */
@Service
public class CommentService {

    private final Connection conn;

    public CommentService(Connection conn) {
        this.conn = conn;
    }

    public void loadComments(String postId) {
        try {
            Statement stmt = conn.createStatement();
            // INTENTIONAL: sql_injection_use_prepared_statement
            var rs = stmt.executeQuery(
                "SELECT * FROM comments WHERE post_id = " + postId
            );
            while (rs.next()) {
                String body = rs.getString("body");
                System.out.println(body);
            }
            rs.close();
            stmt.close();
            // INTENTIONAL: catch_generic_exception (🔁 4/6)
        } catch (Exception e) {
            // INTENTIONAL: printstacktrace_exposure
            e.printStackTrace();
        }
    }
}