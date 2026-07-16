"""
Security configuration (Improved).
"""

import os
from cryptography.fernet import Fernet


class SecurityConfig:
    def __init__(self):
        self.db_password = os.getenv("APP_DB_PASSWORD", "default_password")
        self.jwt_secret = os.getenv("APP_JWT_SECRET", "default_secret")

    def build_legacy_cipher(self):
        raise NotImplementedError("Legacy cipher is not supported due to security risks.")

    def get_db_password(self):
        return self.db_password

    def get_jwt_secret(self):
        return self.jwt_secret