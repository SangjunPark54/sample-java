package com.example.board.repository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 게시글 저장소 (INTENTIONAL VIOLATIONS).
 */
public class PostRepository {

    private final Connection conn;

    /**
     * 생성자를 통해 데이터베이스 연결 객체를 초기화합니다.
     * 
     * @param conn 데이터베이스 연결 객체
     */
    public PostRepository(Connection conn) {
        this.conn = conn;
    }

    /**
     * 특정 작성자의 글을 삭제합니다.
     *
     * @param author 삭제할 글의 작성자
     * @return 삭제된 행의 수를 반환합니다. 예외가 발생한 경우 -1을 반환합니다.
     */
    public int deleteByAuthor(String author) {
        String sql = "DELETE FROM posts WHERE author = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, author);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            // SQLException 발생 시 -1 반환
            return -1;
        }
    }

    /**
     * 주어진 ID를 가진 게시글이 존재하는지 확인합니다.
     *
     * @param id 확인할 게시글의 ID
     * @return 존재하면 true, 그렇지 않으면 false를 반환합니다.
     */
    public boolean existsById(Long id) {
        String sql = "SELECT 1 FROM posts WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            var rs = pstmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            return false;
        }
    }
}