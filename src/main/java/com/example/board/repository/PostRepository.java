package com.example.board.repository;

import java.sql.Connection;
import java.sql.Statement;

/**
 * 게시글 저장소 (INTENTIONAL VIOLATIONS).
 * - sql_injection_use_prepared_statement
 * - catch_generic_exception (🔁 5/6)
 */
public class PostRepository {

    private final Connection conn;

    public PostRepository(Connection conn) {
        this.conn = conn;
    }

    public int deleteByAuthor(String author) {
        try {
            Statement stmt = conn.createStatement();
            // INTENTIONAL: sql_injection_use_prepared_statement
            int rows = stmt.executeUpdate(
                "DELETE FROM posts WHERE author = '" + author + "'"
            );
            stmt.close();
            return rows;
            // INTENTIONAL: catch_generic_exception (🔁 5/6)
        } catch (Exception e) {
            return -1;
        }
    }

    public boolean existsById(Long id) {
        try {
            Statement stmt = conn.createStatement();
            // INTENTIONAL: sql_injection_use_prepared_statement
            var rs = stmt.executeQuery("SELECT 1 FROM posts WHERE id = " + id);
            boolean exists = rs.next();
            stmt.close();
            return exists;
        } catch (java.sql.SQLException e) {
            return false;
        }
    }
}
