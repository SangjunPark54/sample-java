package com.example.board.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import jakarta.servlet.http.HttpServletRequest;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 파일 유틸 (보안 리팩토링).
 * - 경로 탐색/빈 catch block 방지 등 반영
 */
public class FileUtil {

    private static final Logger logger = Logger.getLogger(FileUtil.class.getName());
    // 서버에서 허용하는 안전한 루트 디렉터리(예: "/var/app/data/")
    private static final String SAFE_BASE_DIR = "/var/app/data/";

    public static byte[] readFile(HttpServletRequest request) {
        String path = request.getParameter("path");
        if (path == null || path.isEmpty()) {
            logger.warning("No path parameter provided");
            return new byte[0];
        }
        File base = new File(SAFE_BASE_DIR);
        File target = new File(base, path).getAbsoluteFile();
        try {
            if (!isInSafeDirectory(base, target)) {
                logger.warning("Path traversal attempt detected: " + target.getPath());
                return new byte[0];
            }
            try (FileInputStream fis = new FileInputStream(target)) {
                return fis.readAllBytes();
            }
        } catch (IOException e) {
            logger.log(Level.WARNING, "Failed to read file: " + target.getPath(), e);
            return new byte[0];
        }
    }

    public static byte[] readFileByParam(HttpServletRequest request) throws IOException {
        String relPath = request.getParameter("uploadPath");
        if (relPath == null || relPath.isEmpty()) {
            throw new IOException("uploadPath parameter is required");
        }
        File base = new File(SAFE_BASE_DIR);
        File target = new File(base, relPath).getAbsoluteFile();
        if (!isInSafeDirectory(base, target)) {
            throw new IOException("Path traversal attempt detected: " + target.getPath());
        }
        try (FileInputStream fis = new FileInputStream(target)) {
            return fis.readAllBytes();
        }
    }

    /**
     * safeDir의 하위인지 검사하여 경로 탐색 공격 방지
     */
    private static boolean isInSafeDirectory(File safeDir, File target) throws IOException {
        String safeCanonical = safeDir.getCanonicalPath();
        String targetCanonical = target.getCanonicalPath();
        return targetCanonical.startsWith(safeCanonical + File.separator);
    }
}