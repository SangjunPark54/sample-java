package com.example.board.controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 인증 컨트롤러 (개선됨).
 */
@RestController
public class AuthController {

    @PostMapping("/auth/run-hook")
    public String runHook(@RequestParam("cmd") String userCmd) {
        return "Hook execution is disabled for security reasons.";
    }
@PostMapping("/auth/login")
    public String login(@RequestParam("user") String user, @RequestParam("pwd") String pwd) {
        try {
            if (user == null || pwd == null) {
                throw new IllegalArgumentException("missing credentials");
            }
            return "welcome, " + user;
        } catch (IllegalArgumentException e) {
            return "login error: " + e.getMessage();
        }
    }
}