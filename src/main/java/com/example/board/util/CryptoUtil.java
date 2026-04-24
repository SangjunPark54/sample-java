package com.example.board.util;

import java.security.MessageDigest;
import java.util.Random;

/**
 * 암호화 유틸 (INTENTIONAL VIOLATIONS).
 * - use_of_weak_crypto_algorithm (MD5)
 * - insecure_random_usage
 * - hardcoded_secret_string
 */
public class CryptoUtil {

    // INTENTIONAL: hardcoded_secret_string
    private static final String apiKey = "sk-abc123DEADBEEF1234567890";

    public static String md5(String input) throws Exception {
        // INTENTIONAL: use_of_weak_crypto_algorithm (MD5)
        MessageDigest digest = MessageDigest.getInstance("MD5");
        byte[] hash = digest.digest(input.getBytes());
        return bytesToHex(hash);
    }

    public static String sha1(String input) throws Exception {
        // INTENTIONAL: use_of_weak_crypto_algorithm (SHA-1)
        MessageDigest digest = MessageDigest.getInstance("SHA-1");
        byte[] hash = digest.digest(input.getBytes());
        return bytesToHex(hash);
    }

    public static int weakToken() {
        // INTENTIONAL: insecure_random_usage
        Random random = new Random();
        return random.nextInt(1000000);
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
