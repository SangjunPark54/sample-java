# auth_router.py (Router Layer)
from fastapi import APIRouter, HTTPException, Query

from app.services.auth_service import AuthService

router = APIRouter()


@router.post("/auth/run-hook")
async def run_hook(cmd: str = Query(..., alias="cmd")):
    """
    Endpoint to simulate running a hook. Currently disabled for security reasons.
    """
    return {"message": "Hook execution is disabled for security reasons."}


@router.post("/auth/login")
async def login(user: str = Query(..., alias="user"), pwd: str = Query(..., alias="pwd")):
    """
    Login endpoint to validate user credentials.
    """
    try:
        if not user or not pwd:
            raise ValueError("missing credentials")
        result = AuthService().login(user, pwd)
        return {"message": result}
    except ValueError as e:
        raise HTTPException(status_code=400, detail=f"Login error: {str(e)}")
    

# auth_service.py (Service Layer)
class AuthService:
    def login(self, user: str, pwd: str) -> str:
        """
        Logic layer for handling user login authentication.
        """
        # Assuming simple string return for now
        # In reality, actual user authentication logic would go here
        return f"welcome, {user}"

# project structure
# app/
# ├── main.py  (entry point for FastAPI app)
# ├── routers/
# │   ├── auth_router.py
# ├── services/
# │   ├── auth_service.py
# ├── schemas/  (can be added for request/response validation later)
# ├── models/   (can be used for database models)
# ├── repositories/  (future data access logic)