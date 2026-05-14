package com.dbahen.GymTracker.controller;

import com.dbahen.GymTracker.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
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
}
