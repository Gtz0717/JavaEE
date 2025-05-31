package com.test_e.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {
    private static final Logger logger = LogManager.getLogger(TestController.class);

    @GetMapping("/test")
    @ResponseBody
    public String testLog() {
        logger.debug("DEBUG 测试");
        logger.info("INFO 测试");
        logger.warn("WARN 测试");
        logger.error("ERROR 测试");
        return "Log4j2 日志测试完成！";
    }
}