# app/router/post_router.py
from fastapi import APIRouter, HTTPException, Request, Response, Path, Query
from app.service.post_service import PostService
from app.schema.post import PostCreateRequest
import os
import html

router = APIRouter()
service = PostService()

@router.get("/posts/{id}/render")
async def render_post(id: int, response: Response):
    post = service.find_by_id(id)
    if not post:
        raise HTTPException(status_code=404, detail="Post not found")
    response.headers["Content-Type"] = "text/html"
    return f"{escape_html(post['content'])}<br>{escape_html(post['title'])}"

@router.get("/posts/attachments")
async def read_attachment(file: str = Query(None)):
    if not file or ".." in file:
        raise HTTPException(status_code=400, detail="Invalid file name")
    try:
        file_path = os.path.normpath(f"/uploads/{file}")
        if not os.path.isfile(file_path) or not file_path.startswith("/uploads"):
            raise HTTPException(status_code=400, detail="Invalid file path")
        return {"file_length": os.path.getsize(file_path)}
    except Exception as e:
        raise HTTPException(status_code=500, detail="Error accessing file") from e

@router.post("/posts")
async def create_post(body: PostCreateRequest):
    if not body:
        raise HTTPException(status_code=400, detail="Post body cannot be null")
    return {"status": "created"}

@router.delete("/posts/{id}")
async def delete_post(id: int):
    service.remove_by_author("admin")
    return {"status": f"deleted {id}"}

def escape_html(input: str) -> str:
    return html.escape(input)

# app/service/post_service.py
class PostService:
    def find_by_id(self, id: int):
        # Add real DB lookup logic
        return {"id": id, "content": "Post content", "title": "Post title"}

    def remove_by_author(self, author: str):
        # Add real removal logic
        pass

# app/schema/post.py
from pydantic import BaseModel

class PostCreateRequest(BaseModel):
    title: str
    content: str