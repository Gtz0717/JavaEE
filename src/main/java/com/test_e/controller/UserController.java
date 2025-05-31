package com.test_e.controller;


import com.test_e.entity.User;
import com.test_e.exception.BusinessException;
import com.test_e.service.UserService;

import com.test_e.utils.JwtTokenUtil;
import com.test_e.utils.Result;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    /**
     * 用户登录
     */
    @GetMapping("/login")  // 现在映射到 /login
    public String showLoginForm(@ModelAttribute("error") String error, Model model) {
        if (error != null) {
            model.addAttribute("error", error);
        }
        model.addAttribute("user", new User()); // 添加表单绑定对象
        return "login";
    }

    @PostMapping("/login")  // 现在映射到 /login
    public String handleLogin(
            @RequestParam String username,
            @RequestParam String password,
            HttpSession session,
            RedirectAttributes redirectAttrs) {

        try {
            User user = userService.login(username, password);
            session.setAttribute("currentUser", user);

            String redirectUrl;
            switch (user.getRole()) {
                case "admin":
                    redirectUrl = "redirect:/admin/dashboard";
                    break;
                case "teacher":
                    redirectUrl = "redirect:/course/add";
                    break;
                case "student":
                    redirectUrl = "redirect:/student/home";
                    break;
                default:
                    throw new BusinessException("未知角色");
            }
            return redirectUrl;
        } catch (BusinessException e) {
            redirectAttrs.addFlashAttribute("error", e.getMessage());
            return "redirect:/login"; // 保持统一
        }
    }
    /**
     * 用户登出
     */
    @GetMapping("/logout")
    @ResponseBody
    public Result logout(HttpSession session) {
        session.invalidate();
        return Result.success("登出成功");
    }

    /**
     * 修改密码
     */
    @PostMapping("/changePassword")
    @ResponseBody
    public Result changePassword(@ModelAttribute("currentUser") User currentUser,
                                 @NotBlank String oldPassword,
                                 @Size(min=8) String newPassword,
                                 HttpSession session) {
        checkLogin(currentUser);
        if(oldPassword.equals(newPassword)) {
            throw new BusinessException("新密码不能与旧密码相同");
        }

        userService.changePassword(currentUser.getUserId(), oldPassword, newPassword);

        // 密码修改后使会话失效
        session.invalidate();
        return Result.success("密码修改成功，请重新登录");
    }
    /**
     * 获取当前用户信息
     */
    @GetMapping("/profile")
    public ResponseEntity<?> getProfile(HttpServletRequest request) {
        String username = (String) request.getAttribute("username");
        return ResponseEntity.ok("欢迎访问, " + username + "! 这是您的个人资料");
    }


    @GetMapping("/admin")
    public ResponseEntity<?> adminOnly() {
        return ResponseEntity.ok("这是管理员专属区域");
    }
}