package com.test_e.dao;

import com.test_e.entity.Operation_log;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OperationLogMapper {
    /**
     * 查询操作日志
     * @param startTime 开始时间(可选)
     * @param endTime 结束时间(可选)
     * @param operation 操作类型(可选)
     * @return 操作日志列表
     */
    List<Operation_log> selectOperationLogs(@Param("startTime") String startTime,
                                            @Param("endTime") String endTime,
                                            @Param("operation") String operation);

    /**
     * 插入操作日志
     * @param log 操作日志对象
     * @return 影响行数
     */
    int insertOperationLog(Operation_log log);
}