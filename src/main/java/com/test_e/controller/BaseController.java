package com.test_e.controller;

import com.test_e.entity.User;
import com.test_e.exception.BusinessException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.ModelAttribute;

public class BaseController {

    /**
     * 获取当前登录用户
     */
    private User getCurrentUser(HttpSession session) {
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null) {
            throw new BusinessException("请先登录"); // 抛出异常，由全局异常处理器处理
        }
        return currentUser;
    }
//    @ModelAttribute("currentUser")
//    public User getCurrentUser(HttpServletRequest request) {
//        HttpSession session = request.getSession(false);
//        if (session != null) {
//            return (User) session.getAttribute("user");
//        }
//        return null;
//    }

    /**
     * 检查用户是否登录
     */
    protected void checkLogin(User user) {
        if (user == null) {
            throw new RuntimeException("用户未登录");
        }
    }

    /**
     * 检查用户角色
     */
    protected void checkRole(User user, String role) {
        if (user == null) {
            throw new RuntimeException("请先登录"); // 这里会触发全局异常处理
        }
        if (!user.getRole().equals(role)) {
            throw new RuntimeException("权限不足");
        }
    }
}