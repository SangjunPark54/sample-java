package com.example.board.controller;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.board.model.Post;
import com.example.board.service.post_service;

/**
 * 게시글 컨트롤러.
 */
@RestController
public class PostController {

    private final post_service service;

    public PostController(post_service service) {
        this.service = service;
    }
@GetMapping("/posts/{id}/render")
    public void renderPost(@PathVariable Long id, HttpServletResponse response) throws IOException {
        Post post = service.findById(id);
        if (post == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Post not found");
            return;
        }
        response.setContentType("text/html");
        response.getWriter().print(escapeHtml(post.getContent()));
        response.getWriter().println(escapeHtml(post.getTitle()));
    }
@GetMapping("/posts/attachments")
    public long readAttachment(HttpServletRequest request) {
        try {
            String requestedFileName = request.getParameter("file");
            if (requestedFileName == null || requestedFileName.contains("..")) {
                throw new SecurityException("Invalid file name");
            }

            String filePath = Paths.get("/uploads", requestedFileName).normalize().toString();
            File f = new File(filePath);
            if (!f.exists() || !f.getCanonicalPath().startsWith("/uploads")) {
                throw new SecurityException("Invalid file path");
            }
            return f.length();
        } catch (Exception e) {
            throw new RuntimeException("Error accessing file", e);
        }
    }
@PostMapping("/posts")
    public String createPost(@RequestBody Post body) {
        if (body == null) {
            throw new IllegalArgumentException("Post body cannot be null");
        }
        return "created";
    }
@DeleteMapping("/posts/{id}")
    public String deletePost(@PathVariable Long id) {
        service.removeByAuthor("admin");
        return "deleted " + id;
    }

    private String escapeHtml(String input) {
        return input.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;").replace("'", "&#x27;");
    }
}