from fastapi import APIRouter, HTTPException, Form

router = APIRouter()

@router.post("/auth/run-hook")
async def run_hook(cmd: str = Form(...)):
    return "Hook execution is disabled for security reasons."

@router.post("/auth/login")
async def login(user: str = Form(...), pwd: str = Form(...)):
    if not user or not pwd:
        raise HTTPException(status_code=400, detail="missing credentials")
    return {"message": f"welcome, {user}"}