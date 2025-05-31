package com.test_e.controller;

import com.test_e.entity.User;
import com.test_e.service.UserService;
import com.test_e.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginRequest) {
        String username = loginRequest.get("username");
        String password = loginRequest.get("password");

        // 验证用户名密码
        User user = userService.login(username, password);
        if (user == null || !user.getPassword().equals(password)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        // 生成token
        String token = jwtTokenUtil.generateToken(username);

        Map<String, String> response = new HashMap<>();
        response.put("token", token);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.substring(7);

        if (jwtTokenUtil.isTokenAboutToExpire(token, 300)) { // 5分钟内过期可刷新
            String newToken = jwtTokenUtil.generateToken(jwtTokenUtil.getUsernameFromToken(token));

            Map<String, String> response = new HashMap<>();
            response.put("token", newToken);

            return ResponseEntity.ok(response);
        }

        return ResponseEntity.badRequest().body("Token cannot be refreshed");
    }
}