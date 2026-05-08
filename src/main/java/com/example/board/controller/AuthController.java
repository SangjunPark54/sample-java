package com.example.board.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 인증 컨트롤러 (보안 이슈 수정).
 */
@RestController
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    /**
     * 실제 운영 환경에서는 이와 같이 사용자 입력을 외부 명령 실행에 절대 사용하지 않아야 합니다.
     * 보안상의 이유로 이 기능은 비활성화되었습니다.
     */
    @PostMapping("/auth/run-hook")
    public String runHook(@RequestParam("cmd") String userCmd) {
        // 보안 취약점 제거: 외부 명령 실행 불가
        logger.warn("runHook endpoint called with cmd='{}'. This API is disabled for security reasons.", userCmd);
        return "This API is disabled for security reasons.";
    }

    @PostMapping("/auth/login")
    public String login(@RequestParam("user") String user, @RequestParam("pwd") String pwd) {
        try {
            if (user == null || pwd == null) {
                throw new IllegalArgumentException("missing credentials");
            }
            return "welcome, " + user;
        } catch (IllegalArgumentException e) {
            return "login error: missing credentials";
        } catch (Exception e) {
            logger.error("Unexpected error on login", e);
            return "login error";
        }
    }
}