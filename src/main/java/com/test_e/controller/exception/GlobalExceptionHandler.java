package com.test_e.controller.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public String handleRuntimeException(RuntimeException ex,
                                         HttpServletRequest request,
                                         RedirectAttributes redirectAttrs) {
        if ("请先登录".equals(ex.getMessage())) {
            redirectAttrs.addFlashAttribute("error", "请先登录系统");
            return "redirect:/login";
        }
        // 其他异常处理...
        redirectAttrs.addFlashAttribute("error", ex.getMessage());
        return "redirect:/error";
    }
}