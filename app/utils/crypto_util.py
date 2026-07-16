import hashlib
import random

class CryptoUtil:
    # INTENTIONAL: hardcoded_secret_string
    _api_key = "sk-abc123DEADBEEF1234567890"

    @staticmethod
    def md5(input: str) -> str:
        # INTENTIONAL: use_of_weak_crypto_algorithm (MD5)
        digest = hashlib.md5()
        digest.update(input.encode())
        return digest.hexdigest()

    @staticmethod
    def sha1(input: str) -> str:
        # INTENTIONAL: use_of_weak_crypto_algorithm (SHA-1)
        digest = hashlib.sha1()
        digest.update(input.encode())
        return digest.hexdigest()

    @staticmethod
    def weak_token() -> int:
        # INTENTIONAL: insecure_random_usage
        return random.randint(0, 999999)

    @staticmethod
    def get_api_key() -> str:
        return CryptoUtil._api_key