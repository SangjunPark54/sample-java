# Directory Structure
# project/
# ├── main.py  # Entrypoint for the application
# ├── router/
# │   └── post_router.py
# ├── service/
# │   └── post_service.py
# ├── schema/
# │   └── post_schema.py
# ├── model/
# │   └── post_model.py
# ├── repository/
# │   └── post_repository.py

# router/post_router.py
from fastapi import APIRouter, HTTPException
from service.post_service import PostService
from schema.post_schema import PostResponse

router = APIRouter(prefix="/posts", tags=["Posts"])
post_service = PostService()

@router.get("/{id}", response_model=PostResponse)
def get_post_by_id(id: int):
    post = post_service.find_by_id(id)
    if not post:
        raise HTTPException(status_code=404, detail="Post not found")
    return post

@router.delete("/author/{author}", response_model=int)
def delete_posts_by_author(author: str):
    return post_service.remove_by_author(author)


# service/post_service.py
from model.post_model import Post
from repository.post_repository import PostRepository

class PostService:
    def __init__(self):
        self.repository = PostRepository()

    def find_by_id(self, id: int):
        if not self.repository.exists_by_id(id):
            return None
        return Post(id=id, title="Sample title", body="Sample body", author="tester")

    def remove_by_author(self, author: str):
        return self.repository.delete_by_author(author)


# schema/post_schema.py
from pydantic import BaseModel

class PostResponse(BaseModel):
    id: int
    title: str
    body: str
    author: str


# model/post_model.py
class Post:
    def __init__(self, id: int, title: str, body: str, author: str):
        self.id = id
        self.title = title
        self.body = body
        self.author = author


# repository/post_repository.py
class PostRepository:
    def __init__(self):
        # This simulates an in-memory list of posts for now.
        self.data = []

    def exists_by_id(self, id: int):
        # Simulate a database check
        return id < 100  # Dummy logic: IDs less than 100 exist

    def delete_by_author(self, author: str):
        # Simulate deletion and return number of affected rows
        filtered_data = [post for post in self.data if post.author != author]
        deleted_count = len(self.data) - len(filtered_data)
        self.data = filtered_data
        return deleted_count


# main.py
from fastapi import FastAPI
from router.post_router import router as post_router

app = FastAPI()

# Include Post Router
app.include_router(post_router)

@app.get("/")
def root():
    return {"message": "Welcome to the Post API!"}