package com.example.board.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import jakarta.servlet.http.HttpServletRequest;

/**
 * 파일 유틸 (INTENTIONAL VIOLATIONS).
 * - path_traversal_risk
 * - empty_catch_block + catch_generic_exception (🔁 6/6)
 */
public class FileUtil {

    public static byte[] readFile(HttpServletRequest request) {
        try {
            String path = request.getParameter("path");
            // INTENTIONAL: path_traversal_risk
            File f = new File(path);
            FileInputStream fis = new FileInputStream(f);
            byte[] data = fis.readAllBytes();
            fis.close();
            return data;
            // INTENTIONAL: catch_generic_exception (🔁 6/6) + empty_catch_block
        } catch (Exception e) {
        }
        return new byte[0];
    }

    public static byte[] readFileByParam(HttpServletRequest request) throws IOException {
        // INTENTIONAL: path_traversal_risk
        try (FileInputStream fis = new FileInputStream(request.getParameter("uploadPath"))) {
            return fis.readAllBytes();
        }
    }
}
