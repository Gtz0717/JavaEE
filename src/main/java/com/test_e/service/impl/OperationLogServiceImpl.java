package com.test_e.service.impl;

import com.test_e.entity.Operation_log;
import com.test_e.dao.OperationLogMapper;
import com.test_e.service.OperationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OperationLogServiceImpl implements OperationLogService {

    @Autowired
    private OperationLogMapper operationLogMapper;

    @Override
    public List<Operation_log> getOperationLogs(String startTime, String endTime, String operation) {
        return operationLogMapper.selectOperationLogs(startTime, endTime, operation);
    }

    @Override
    public void recordOperationLog(Operation_log log) {
        operationLogMapper.insertOperationLog(log);
    }
}