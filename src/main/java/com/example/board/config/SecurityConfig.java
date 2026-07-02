package com.example.board.config;
import javax.crypto.Cipher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 보안 설정 (개선됨).
 */
@Configuration
public class SecurityConfig {

    @Value("${app.db.password}")
    private String dbPassword;
@Value("${app.jwt.secret}")
    private String jwtSecret;

    public Cipher buildLegacyCipher() throws Exception {
        throw new UnsupportedOperationException("Legacy cipher is not supported due to security risks.");
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public String getJwtSecret() {
        return jwtSecret;
    }
}