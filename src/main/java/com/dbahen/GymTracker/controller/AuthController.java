package com.dbahen.GymTracker.controller;

import com.dbahen.GymTracker.service.PasswordResetService;
import com.dbahen.GymTracker.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;
    private final PasswordResetService passwordResetService;

    public AuthController(UserService userService, PasswordResetService passwordResetService) {
        this.userService = userService;
        this.passwordResetService = passwordResetService;
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody Map<String, String > body) {
        String token = userService.register(body.get("email"), body.get("password"));
        return ResponseEntity.ok(Map.of("token", token));
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> body) {
        String token = userService.login(body.get("email"), body.get("password"));
        return ResponseEntity.ok(Map.of("token", token));
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody Map<String, String> body) {
        String frontendUrl = "https://gymtracker-frontend-six.vercel.app";
        passwordResetService.forgotPassword(body.get("email"), frontendUrl);
        return ResponseEntity.ok("Email enviado");
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody Map<String, String> body) {
        passwordResetService.resetPassword(body.get("token"), body.get("password"));
        return ResponseEntity.ok("Contraseña actualizada");
    }
}
