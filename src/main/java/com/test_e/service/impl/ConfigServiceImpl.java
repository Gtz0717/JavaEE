package com.test_e.service.impl;

import com.test_e.entity.Config;
import com.test_e.dao.ConfigMapper;
import com.test_e.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ConfigServiceImpl implements ConfigService {

    @Autowired
    private ConfigMapper configMapper;

    @Override
    public List<Config> getAllConfigs() {
        return configMapper.selectAllConfigs();
    }

    @Override
    public Config getConfigByKey(String key) {
        return configMapper.selectConfigByKey(key);
    }

    @Override
    public void updateConfig(Config config) {
        configMapper.updateConfig(config);
    }
}