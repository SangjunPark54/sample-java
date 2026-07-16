# src/models/user.py

import os
from pydantic import BaseModel, EmailStr


class User(BaseModel):
    """
    사용자 도메인 모델.
    """

    id: int | None = None
    username: str
    email: EmailStr

    # 환경변수에서 API 토큰을 가져옵니다.
    @property
    def api_token(self) -> str | None:
        return os.getenv("API_TOKEN")