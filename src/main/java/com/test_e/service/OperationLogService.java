package com.test_e.service;

import com.test_e.entity.Operation_log;
import java.util.List;

public interface OperationLogService {
    /**
     * 获取操作日志列表
     * @param startTime 开始时间(可选)
     * @param endTime 结束时间(可选)
     * @param operation 操作类型(可选)
     * @return 操作日志列表
     */
    List<Operation_log> getOperationLogs(String startTime, String endTime, String operation);

    /**
     * 记录操作日志
     * @param log 操作日志对象
     */
    void recordOperationLog(Operation_log log);
}