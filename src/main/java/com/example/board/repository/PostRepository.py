# File: src/repository/post_repository.py

import sqlite3
from typing import Optional
import logging

class PostRepository:
    """
    Repository for managing posts in the database.
    """

    def __init__(self, database_path: str):
        """
        Initialize the repository with a database connection path.

        :param database_path: The file path to the SQLite database.
        """
        self.database_path = database_path
        self.logger = logging.getLogger(self.__class__.__name__)

    def _get_connection(self):
        """
        Private helper method to get a database connection.
        """
        return sqlite3.connect(self.database_path)

    def delete_by_author(self, author: str) -> int:
        """
        Deletes posts authored by the given author.

        :param author: The author whose posts should be deleted.
        :return: The number of rows deleted or -1 if an exception occurred.
        """
        sql = "DELETE FROM posts WHERE author = ?"
        try:
            with self._get_connection() as conn:
                cursor = conn.cursor()
                cursor.execute(sql, (author,))
                deleted_count = cursor.rowcount
                return deleted_count
        except sqlite3.DatabaseError as e:
            self.logger.error("Error occurred while deleting posts by author: %s. Error: %s", author, e)
            return -1

    def exists_by_id(self, post_id: int) -> bool:
        """
        Checks if a post with the given ID exists in the repository.

        :param post_id: The ID of the post to check.
        :return: True if the post exists, otherwise False.
        """
        sql = "SELECT 1 FROM posts WHERE id = ?"
        try:
            with self._get_connection() as conn:
                cursor = conn.cursor()
                cursor.execute(sql, (post_id,))
                result = cursor.fetchone()
                return result is not None
        except sqlite3.DatabaseError as e:
            self.logger.error("Error occurred while checking existence of post with ID: %d. Error: %s", post_id, e)
            return False