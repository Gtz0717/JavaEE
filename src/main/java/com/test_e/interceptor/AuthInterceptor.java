package com.test_e.interceptor;
import com.test_e.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler
    ) throws Exception {
        // 1. 获取当前 Session
        HttpSession session = request.getSession(false); // 如果不存在，返回 null

        // 2. 检查是否登录（示例：检查 Session 中的 currentUser）
        if (session == null || session.getAttribute("currentUser") == null) {
            // 未登录，重定向到登录页
            response.sendRedirect(request.getContextPath() + "/login");
            return false; // 终止请求
        }

        // 3. 检查用户角色（可选，例如只允许教师访问 /course/**）
        User currentUser = (User) session.getAttribute("currentUser");
        String requestURI = request.getRequestURI();

        if (requestURI.startsWith("/course/") && !"teacher".equals(currentUser.getRole())) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "无权访问");
            return false;
        }

        // 4. 放行请求
        return true;
    }
}