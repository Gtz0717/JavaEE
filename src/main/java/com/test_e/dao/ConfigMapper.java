package com.test_e.dao;

import com.test_e.entity.Config;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ConfigMapper {
    /**
     * 查询所有配置
     */
    List<Config> selectAllConfigs();

    /**
     * 根据key查询配置
     */
    Config selectConfigByKey(String key);

    /**
     * 更新配置
     */
    int updateConfig(Config config);
}