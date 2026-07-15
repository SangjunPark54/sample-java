# File: utils/file_util.py

import os
from fastapi import HTTPException, Request

class FileUtil:
    @staticmethod
    async def read_file(request: Request) -> bytes:
        try:
            path = request.query_params.get("path")
            if path is None or ".." in path:
                raise HTTPException(status_code=400, detail="Invalid file path.")
            
            # Validate file path
            canonical_path = os.path.realpath(path)
            if not os.path.exists(path) or not canonical_path.startswith("/secure-base-dir"):
                raise HTTPException(status_code=403, detail="Access denied for file path.")

            # Read the file content
            with open(canonical_path, "rb") as file:
                return file.read()
        except Exception as e:
            raise HTTPException(status_code=500, detail=f"Error reading file: {str(e)}")

    @staticmethod
    async def read_file_by_param(request: Request) -> bytes:
        try:
            upload_path = request.query_params.get("uploadPath")
            if upload_path is None or ".." in upload_path:
                raise HTTPException(status_code=400, detail="Invalid file path.")
            
            # Validate file path
            canonical_path = os.path.realpath(upload_path)
            if not os.path.exists(upload_path) or not canonical_path.startswith("/secure-base-dir"):
                raise HTTPException(status_code=403, detail="Access denied for file path.")

            # Read the file content
            with open(canonical_path, "rb") as file:
                return file.read()
        except Exception as e:
            raise HTTPException(status_code=500, detail=f"Error reading file: {str(e)}")