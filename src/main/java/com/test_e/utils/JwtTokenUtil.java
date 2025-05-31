package com.test_e.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenUtil {

    @Value("${jwt.secret}")
    private String secret; // Base64 编码的密钥

    @Value("${jwt.expiration}")
    private Long expiration; // 过期时间（秒）

    private Key key; // 安全密钥（HMAC-SHA）

    @PostConstruct
    public void init() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        this.key = Keys.hmacShaKeyFor(keyBytes); // 自动推断算法（如 HS256）
    }

    // 生成 Token
    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder()
                .setClaims(claims) // 设置自定义声明（可选）
                .setSubject(username) // 设置主题（通常是用户名）
                .setIssuedAt(new Date()) // 设置签发时间
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000)) // 设置过期时间
                .signWith(key) // 签名（自动选择算法）
                .compact(); // 生成最终 Token
    }

    // 解析 Token（带完整异常处理）
    public Claims parseToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(key) // 验证签名 - 在 0.12.3 中使用 setSigningKey
                    .build()
                    .parseClaimsJws(token) // 解析已签名的 Token - 在 0.12.3 中使用 parseClaimsJws
                    .getBody(); // 获取 Claims（负载）- 在 0.12.3 中使用 getBody
        } catch (ExpiredJwtException e) {
            throw new RuntimeException("Token 已过期", e);
        } catch (MalformedJwtException e) {
            throw new RuntimeException("Token 格式错误", e);
        } catch (JwtException | IllegalArgumentException e) {
            throw new RuntimeException("无效的 Token", e);
        }
    }

    // 从 Token 获取用户名（优化异常处理）
    public String getUsernameFromToken(String token) {
        try {
            return parseToken(token).getSubject();
        } catch (RuntimeException e) {
            throw new RuntimeException("无法从 Token 提取用户名", e);
        }
    }

    // 验证 Token 是否有效（优化逻辑）
    public boolean validateToken(String token) {
        try {
            Claims claims = parseToken(token);
            return !claims.getExpiration().before(new Date()); // 检查是否过期
        } catch (RuntimeException e) {
            return false; // 任何异常均视为无效
        }
    }

    // 检查 Token 是否即将过期（优化逻辑）
    public boolean isTokenAboutToExpire(String token, long thresholdSeconds) {
        try {
            Date expiration = parseToken(token).getExpiration();
            long currentTime = System.currentTimeMillis();
            long thresholdTime = currentTime + thresholdSeconds * 1000;
            return expiration.getTime() <= thresholdTime; // 是否在阈值时间内过期
        } catch (RuntimeException e) {
            return true; // 解析失败视为需要刷新
        }
    }
}