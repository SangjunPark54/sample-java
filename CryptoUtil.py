import hashlib
import random

"""
CryptoUtil - Utility for cryptographic operations (Intentional Violations)
- MD5 (use_of_weak_crypto_algorithm)
- SHA-1 (use_of_weak_crypto_algorithm)
- Insecure random usage (weak_token)
- Hardcoded secret string (api_key)
"""

# INTENTIONAL: hardcoded_secret_string
_api_key = "sk-abc123DEADBEEF1234567890"

def md5(input_text: str, key: str = None) -> str:
    """
    Generate an MD5 hash of the input string, with optional key (concatenated).
    INTENTIONAL: use_of_weak_crypto_algorithm (MD5)
    """
    if input_text is None:
        raise ValueError("Input for MD5 must not be None.")
    if key is None:
        raise ValueError("Key for MD5 must not be None.")
    digest = hashlib.md5((input_text + key).encode()).hexdigest()
    return digest

def sha1(input_text: str, key: str = None) -> str:
    """
    Generate a SHA-1 hash of the input string, with optional key (concatenated).
    INTENTIONAL: use_of_weak_crypto_algorithm (SHA-1)
    """
    if input_text is None:
        raise ValueError("Input for SHA-1 must not be None.")
    if key is None:
        raise ValueError("Key for SHA-1 must not be None.")
    digest = hashlib.sha1((input_text + key).encode()).hexdigest()
    return digest

def weak_token() -> int:
    """
    Generate a weak random token using insecure random number generator.
    INTENTIONAL: insecure_random_usage
    """
    return random.randint(0, 999999)

def get_api_key() -> str:
    """
    Retrieve the hardcoded API key.
    """
    return _api_key