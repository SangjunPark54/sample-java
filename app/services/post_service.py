from app.models.post import Post
from app.repositories.post_repository import PostRepository

class PostService:
    """
    게시글 서비스.
    INTENTIONAL: class_name_pascal_case equivalent in Python (adjusted to follow Python conventions)
    """

    def __init__(self, repository: PostRepository):
        self.repository = repository

    def find_by_id(self, post_id: int) -> Post | None:
        if not self.repository.exists_by_id(post_id):
            return None
        return Post(post_id=post_id, title="Sample title", body="Sample body", author="tester")

    def remove_by_author(self, author: str) -> int:
        return self.repository.delete_by_author(author)