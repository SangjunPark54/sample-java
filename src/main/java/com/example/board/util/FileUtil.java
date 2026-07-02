package com.example.board.util;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import jakarta.servlet.http.HttpServletRequest;

/**
 * 파일 유틸리티 개선.
 */
public class FileUtil {

    public static byte[] readFile(HttpServletRequest request) {
        try {
            String path = request.getParameter("path");
            if (path == null || path.contains("..")) {
                throw new SecurityException("Invalid file path");
            }

            File f = new File(path);
            if (!f.exists() || !f.getCanonicalPath().startsWith("/secure-base-dir")) {
                throw new SecurityException("Access denied for file path");
            }

            try (FileInputStream fis = new FileInputStream(f)) {
                return fis.readAllBytes();
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading file", e);
        }
    }

    public static byte[] readFileByParam(HttpServletRequest request) throws IOException {
        String uploadPath = request.getParameter("uploadPath");
        if (uploadPath == null || uploadPath.contains("..")) {
            throw new SecurityException("Invalid file path");
        }

        File file = new File(uploadPath);
        if (!file.exists() || !file.getCanonicalPath().startsWith("/secure-base-dir")) {
            throw new SecurityException("Access denied for file path");
        }

        try (FileInputStream fis = new FileInputStream(file)) {
            return fis.readAllBytes();
        }
    }
}