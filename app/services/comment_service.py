from contextlib import closing

class CommentService:
    """
    Comment service (refactored from Java to Python).
    """

    def __init__(self, data_source):
        self.data_source = data_source

    def load_comments(self, post_id):
        sql = "SELECT * FROM comments WHERE post_id = %s"
        try:
            with closing(self.data_source.get_connection()) as conn:
                with closing(conn.cursor()) as cursor:
                    cursor.execute(sql, (post_id,))
                    for row in cursor.fetchall():
                        print(row["body"])
        except Exception as e:
            # Safe logging or user-friendly error handling.
            print(f"An error occurred: {str(e)}")