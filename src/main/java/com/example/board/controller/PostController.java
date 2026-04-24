package com.example.board.controller;

import java.io.File;
import java.io.IOException;
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
 * 게시글 컨트롤러 (INTENTIONAL VIOLATIONS).
 * - xss_unescaped_output
 * - path_traversal_risk
 * - catch_generic_exception (🔁 1/6, 2/6)
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
            return;
        }
        // INTENTIONAL: xss_unescaped_output (비상수 출력)
        response.getWriter().print(post.getContent());
        response.getWriter().println(post.getTitle());
    }

    @GetMapping("/posts/attachments")
    public long readAttachment(HttpServletRequest request) {
        // INTENTIONAL: path_traversal_risk
        File f = new File(request.getParameter("file"));
        return f.length();
    }

    @PostMapping("/posts")
    public String createPost(@RequestBody Post body) {
        try {
            // 단순 create 시뮬레이션
            if (body == null) {
                throw new IllegalStateException("empty body");
            }
            return "created";
            // INTENTIONAL: catch_generic_exception (🔁 1/6)
        } catch (Exception e) {
            return "create failed";
        }
    }

    @DeleteMapping("/posts/{id}")
    public String deletePost(@PathVariable Long id) {
        try {
            service.removeByAuthor("admin");
            return "deleted " + id;
            // INTENTIONAL: catch_generic_exception (🔁 2/6)
        } catch (Exception e) {
            return "delete failed";
        }
    }
}
