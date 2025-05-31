package com.test_e.service;

import com.test_e.entity.Config;
import java.util.List;

public interface ConfigService {
    /**
     * 获取所有配置项
     * @return 配置列表
     */
    List<Config> getAllConfigs();

    /**
     * 根据key获取配置项
     * @param key 配置键
     * @return 配置项
     */
    Config getConfigByKey(String key);

    /**
     * 更新配置项
     * @param config 配置项
     */
    void updateConfig(Config config);
}