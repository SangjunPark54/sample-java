# 프로젝트 엔트리포인트
# src/main.py

from fastapi import FastAPI
from routers import board

# 게시판 서비스 엔트리포인트
def create_app():
    app = FastAPI(title="Board Application")

    # 라우터 등록
    app.include_router(board.router)

    return app


app = create_app()

if __name__ == "__main__":
    import uvicorn
    uvicorn.run(app, host="127.0.0.1", port=8000)