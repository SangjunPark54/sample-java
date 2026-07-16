from fastapi import APIRouter, HTTPException, Request, Response, Depends
from pydantic import BaseModel
import os
from pathlib import Path
from app.services.post_service import PostService

router = APIRouter()

class Post(BaseModel):
    content: str
    title: str

post_service = PostService()

# Utility function for escaping HTML
def escape_html(input: str) -> str:
    return (
        input.replace("&", "&amp;")
        .replace("<", "&lt;")
        .replace(">", "&gt;")
        .replace("\"", "&quot;")
        .replace("'", "&#x27;")
    )

@router.get("/posts/{id}/render")
def render_post(id: int, response: Response):
    post = post_service.find_by_id(id)
    if not post:
        raise HTTPException(status_code=404, detail="Post not found")
    response.headers["Content-Type"] = "text/html"
    return escape_html(post.content) + "<br>" + escape_html(post.title)

@router.get("/posts/attachments")
def read_attachment(request: Request):
    try:
        requested_file_name = request.query_params.get("file")
        if not requested_file_name or ".." in requested_file_name:
            raise HTTPException(status_code=400, detail="Invalid file name")

        file_path = Path("/uploads").joinpath(requested_file_name).resolve()
        if not file_path.exists() or not str(file_path).startswith(str(Path("/uploads").resolve())):
            raise HTTPException(status_code=400, detail="Invalid file path")

        return {"size": file_path.stat().st_size}
    except Exception as e:
        raise HTTPException(status_code=500, detail="Error accessing file") from e

@router.post("/posts")
def create_post(post: Post):
    if not post:
        raise HTTPException(status_code=400, detail="Post body cannot be null")
    return {"message": "created"}

@router.delete("/posts/{id}")
def delete_post(id: int):
    post_service.remove_by_author("admin")
    return {"message": f"deleted {id}"}