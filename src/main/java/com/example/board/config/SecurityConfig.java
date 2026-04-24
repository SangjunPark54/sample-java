package com.example.board.config;

import javax.crypto.Cipher;
import org.springframework.context.annotation.Configuration;

/**
 * 보안 설정 (INTENTIONAL VIOLATIONS).
 * - hardcoded_secret_string
 * - use_of_weak_crypto_algorithm
 */
@Configuration
public class SecurityConfig {

    // INTENTIONAL: hardcoded_secret_string
    private String dbPassword = "admin1234!";

    // INTENTIONAL: hardcoded_secret_string
    private String jwtSecret = "my-super-secret-jwt-key-do-not-use";

    public Cipher buildLegacyCipher() throws Exception {
        // INTENTIONAL: use_of_weak_crypto_algorithm (DES)
        return Cipher.getInstance("DES");
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public String getJwtSecret() {
        return jwtSecret;
    }
}
