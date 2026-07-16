"""
게시판 서비스 엔트리포인트 (정상 코드 — 대조군)
"""

from fastapi import FastAPI

app = FastAPI()


@app.get("/")
def read_root():
    return {"message": "Hello, Board!"}