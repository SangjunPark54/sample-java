# Project structure
# routers/comment_router.py
from fastapi import APIRouter, Depends, HTTPException
from services.comment_service import CommentService

router = APIRouter()

@router.get("/comments/{post_id}")
async def get_comments(post_id: str, service: CommentService = Depends()):
    return await service.load_comments(post_id)


# services/comment_service.py
from sqlalchemy.ext.asyncio import AsyncSession
from sqlalchemy.future import select
from sqlalchemy.exc import SQLAlchemyError
from repositories.comment_repository import CommentRepository

class CommentService:
    def __init__(self, comment_repository: CommentRepository):
        self.comment_repository = comment_repository

    async def load_comments(self, post_id: str):
        try:
            comments = await self.comment_repository.find_by_post_id(post_id)
            return comments
        except SQLAlchemyError as e:
            # Log the error or re-raise with context (replace with proper logging in production).
            raise HTTPException(status_code=500, detail="Error loading comments") from e


# models/comment_model.py
from sqlalchemy import Column, String, Text, ForeignKey
from sqlalchemy.ext.declarative import declarative_base

Base = declarative_base()

class Comment(Base):
    __tablename__ = "comments"

    id = Column(String, primary_key=True)
    post_id = Column(String, ForeignKey("posts.id"))
    body = Column(Text, nullable=False)


# schemas/comment_schema.py
from pydantic import BaseModel

class CommentSchema(BaseModel):
    id: str
    post_id: str
    body: str

    class Config:
        orm_mode = True


# repositories/comment_repository.py
from sqlalchemy.ext.asyncio import AsyncSession
from sqlalchemy.future import select
from models.comment_model import Comment

class CommentRepository:
    def __init__(self, db_session: AsyncSession):
        self.db_session = db_session

    async def find_by_post_id(self, post_id: str):
        async with self.db_session as session:
            result = await session.execute(select(Comment).filter_by(post_id=post_id))
            comments = result.scalars().all()
        return comments