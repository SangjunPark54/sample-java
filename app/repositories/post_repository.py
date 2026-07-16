import logging
from typing import Optional
import sqlite3

class PostRepository:
    """
    게시글 저장소 (INTENTIONAL VIOLATIONS).
    """

    def __init__(self, database_path: str):
        """
        생성자를 통해 데이터베이스 경로를 초기화합니다.
        """
        self.database_path = database_path
        self.logger = logging.getLogger(self.__class__.__name__)

    def delete_by_author(self, author: str) -> int:
        """
        특정 작성자의 글을 삭제합니다.

        :param author: 삭제할 글의 작성자
        :return: 삭제된 행의 수를 반환합니다. 예외가 발생한 경우 -1을 반환합니다.
        """
        sql = "DELETE FROM posts WHERE author = ?"
        try:
            with sqlite3.connect(self.database_path) as conn:
                cursor = conn.cursor()
                cursor.execute(sql, (author,))
                return cursor.rowcount
        except sqlite3.DatabaseError as e:
            self.logger.error("Error occurred while deleting posts by author: %s", author, exc_info=True)
            return -1

    def exists_by_id(self, post_id: int) -> bool:
        """
        주어진 ID를 가진 게시글이 존재하는지 확인합니다.

        :param post_id: 확인할 게시글의 ID
        :return: 존재하면 True, 그렇지 않으면 False를 반환합니다.
        """
        sql = "SELECT 1 FROM posts WHERE id = ?"
        try:
            with sqlite3.connect(self.database_path) as conn:
                cursor = conn.cursor()
                cursor.execute(sql, (post_id,))
                return cursor.fetchone() is not None
        except sqlite3.DatabaseError as e:
            self.logger.error("Error occurred while checking existence of post with ID: %d", post_id, exc_info=True)
            return False