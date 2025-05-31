package com.test_e.controller;


import com.test_e.entity.Operation_log;
import com.test_e.entity.User;

import com.test_e.service.OperationLogService;
import com.test_e.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/log")
public class OperationLogController extends BaseController {

    @Autowired
    private OperationLogService operationLogService;

    /**
     * 获取操作日志
     */
    @GetMapping("/list")
    @ResponseBody
    public Result getOperationLogs(@ModelAttribute("currentUser") User currentUser,
                                   String startTime, String endTime, String operation) {
        checkRole(currentUser, "admin");
        List<Operation_log> logs = operationLogService.getOperationLogs(startTime, endTime, operation);
        return Result.success(logs);
    }
}