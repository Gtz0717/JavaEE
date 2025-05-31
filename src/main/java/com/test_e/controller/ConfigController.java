package com.test_e.controller;

import com.test_e.entity.Config;
import com.test_e.entity.User;
import com.test_e.controller.BaseController;
import com.test_e.service.ConfigService;
import com.test_e.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/config")
public class ConfigController extends BaseController {

    @Autowired
    private ConfigService configService;

    /**
     * 获取所有配置
     */
    @GetMapping("/list")
    @ResponseBody
    public Result getConfigList(@ModelAttribute("currentUser") User currentUser) {
        checkRole(currentUser, "admin");
        List<Config> configs = configService.getAllConfigs();
        return Result.success(configs);
    }

    /**
     * 更新配置
     */
    @PostMapping("/update")
    @ResponseBody
    public Result updateConfig(@ModelAttribute("currentUser") User currentUser,
                               @RequestBody Config config) {
        checkRole(currentUser, "admin");
        configService.updateConfig(config);
        return Result.success("配置更新成功");
    }

    /**
     * 获取单个配置
     */
    @GetMapping("/get/{key}")
    @ResponseBody
    public Result getConfig(@PathVariable String key) {
        Config config = configService.getConfigByKey(key);
        return Result.success(config);
    }
}