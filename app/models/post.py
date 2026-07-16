from datetime import datetime

class Post:
    """
    게시글 도메인 모델 (정상 코드 — 대조군).
    """

    def __init__(self, id: int = None, title: str = None, content: str = None, author: str = None):
        self.id = id
        self.title = title
        self.content = content
        self.author = author
        self.created_at = datetime.now() if id and title and content and author else None

    def get_id(self) -> int:
        """게시글의 고유 ID를 반환합니다."""
        return self.id

    def get_title(self) -> str:
        """게시글의 제목을 반환합니다."""
        return self.title

    def get_content(self) -> str:
        """게시글의 내용을 반환합니다."""
        return self.content

    def get_author(self) -> str:
        """게시글의 저자를 반환합니다."""
        return self.author

    def get_created_at(self) -> datetime:
        """게시글이 생성된 날짜 및 시간을 반환합니다."""
        return self.created_at