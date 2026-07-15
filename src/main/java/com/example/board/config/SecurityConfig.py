# src/config/security_config.py
from pydantic import BaseSettings
from cryptography.fernet import Fernet


class AppSettings(BaseSettings):
    """
    Application configuration for security settings.
    """
    db_password: str = "default_password"
    jwt_secret: str = "default_secret"

    class Config:
        env_file = ".env"


class SecurityConfig:
    """
    Security configuration (Enhanced).
    """

    def __init__(self, settings: AppSettings):
        self.settings = settings

    def build_legacy_cipher(self):
        raise NotImplementedError("Legacy cipher is not supported due to security risks.")

    def get_db_password(self):
        return self.settings.db_password

    def get_jwt_secret(self):
        return self.settings.jwt_secret


# Example usage:
# from config.security_config import AppSettings, SecurityConfig
# settings = AppSettings()
# security_config = SecurityConfig(settings)