package com.example.board.util;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Optional;

/**
 * 암호화 유틸 (보안 취약점 수정).
 * - 약한 해시 알고리즘 미사용 (SHA-256 적용)
 * - 보안에 안전한 난수 생성(SecureRandom)
 * - 하드코드된 시크릿 제거, 환경 변수에서 관리
 */
public class CryptoUtil {

    // apiKey를 환경 변수(예: BOARD_API_KEY) 또는 다른 설정 파일로부터 주입
    private static final String apiKey = Optional.ofNullable(System.getenv("BOARD_API_KEY"))
        .orElseThrow(() -> new IllegalStateException("BOARD_API_KEY 환경변수가 필요합니다."));

    public static String sha256(String input) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(input.getBytes());
        return bytesToHex(hash);
    }

    public static int secureToken() {
        // 보안에 안전한 토큰 생성
        SecureRandom secureRandom = new SecureRandom();
        return secureRandom.nextInt(1_000_000);
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    public static String getApiKey() {
        return apiKey;
    }
}