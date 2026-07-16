import os
from flask import request


class FileUtil:
    @staticmethod
    def read_file():
        try:
            path = request.args.get('path')
            if path is None or '..' in path:
                raise SecurityError("Invalid file path")

            if not os.path.exists(path) or not os.path.realpath(path).startswith("/secure-base-dir"):
                raise SecurityError("Access denied for file path")

            with open(path, 'rb') as f:
                return f.read()
        except IOError as e:
            raise RuntimeError("Error reading file") from e

    @staticmethod
    def read_file_by_param():
        upload_path = request.args.get('uploadPath')
        if upload_path is None or '..' in upload_path:
            raise SecurityError("Invalid file path")

        if not os.path.exists(upload_path) or not os.path.realpath(upload_path).startswith("/secure-base-dir"):
            raise SecurityError("Access denied for file path")

        with open(upload_path, 'rb') as f:
            return f.read()


class SecurityError(Exception):
    """Custom exception for security errors."""
    pass