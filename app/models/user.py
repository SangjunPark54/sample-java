"""
User domain model.
"""
import os


class User:
    API_TOKEN = os.getenv("API_TOKEN")  # Retrieve API token from environment variables

    def __init__(self, id: int = None, username: str = None, email: str = None):
        self.id = id
        self.username = username
        self.email = email

    def get_id(self) -> int:
        """
        Returns the user ID.
        :return: int: User ID
        """
        return self.id

    def get_username(self) -> str:
        """
        Returns the username.
        :return: str: Username
        """
        return self.username

    def get_email(self) -> str:
        """
        Returns the email of the user.
        :return: str: Email
        """
        return self.email

    @classmethod
    def get_api_token(cls) -> str:
        """
        Returns the API token.
        :return: str: API token
        """
        return cls.API_TOKEN