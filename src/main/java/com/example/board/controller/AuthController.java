package com.example.board.controller;

import java.io.IOException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 인증 컨트롤러 (INTENTIONAL VIOLATIONS).
 * - runtime_exec_with_external_input
 * - empty_catch_block
 * - printstacktrace_exposure
 * - catch_generic_exception (🔁 3/6)
 */
@RestController
public class AuthController {

    @PostMapping("/auth/run-hook")
    public String runHook(@RequestParam("cmd") String userCmd) {
        try {
            // INTENTIONAL: runtime_exec_with_external_input
            Process p = Runtime.getRuntime().exec(userCmd);
            p.waitFor();
            return "executed";
            // INTENTIONAL: empty_catch_block
        } catch (IOException e) {
        } catch (InterruptedException e) {
            // INTENTIONAL: printstacktrace_exposure
            e.printStackTrace();
        }
        return "failed";
    }

    @PostMapping("/auth/login")
    public String login(@RequestParam("user") String user, @RequestParam("pwd") String pwd) {
        try {
            if (user == null || pwd == null) {
                throw new IllegalArgumentException("missing credentials");
            }
            return "welcome, " + user;
            // INTENTIONAL: catch_generic_exception (🔁 3/6) — catch(Throwable)
        } catch (Throwable t) {
            return "login error";
        }
    }
}
