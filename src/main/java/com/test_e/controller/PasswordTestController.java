package com.test_e.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class PasswordTestController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 简单密码测试接口
     * @param password 要测试的密码
     * @return 加密后的密码和验证结果
     */
    @GetMapping("/testPassword")
    public String testPassword(@RequestParam String password) {
        // 加密密码
        String encoded = passwordEncoder.encode(password);

        // 验证密码
        boolean matches = passwordEncoder.matches(password, encoded);

        return "原始密码: " + password + "\n" +
                "加密结果: " + encoded + "\n" +
                "验证结果: " + (matches ? "匹配" : "不匹配");
    }


    @GetMapping("/verifyDbPassword")
    public String verifyDbPassword(
            @RequestParam String inputPassword,
            @RequestParam String dbEncodedPassword) {

        boolean matches = passwordEncoder.matches(inputPassword, dbEncodedPassword);

        return "密码" + (matches ? "匹配" : "不匹配");
    }
}