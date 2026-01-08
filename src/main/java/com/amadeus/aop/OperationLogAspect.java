package com.amadeus.aop;

import com.amadeus.anno.LogOperation;
import com.amadeus.mapper.OperateLogMapper;
import com.amadeus.pojo.OperateLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.Arrays;

@Aspect
@Component
public class OperationLogAspect {

    @Autowired
    private OperateLogMapper operateLogMapper;

    // 环绕通知
    @Around("@annotation(log)")
    public Object around(ProceedingJoinPoint joinPoint, LogOperation log) throws Throwable {
        // 记录开始时间
        long startTime = System.currentTimeMillis();
        // 执行方法
        Object result = joinPoint.proceed();
        // 当前时间
        long endTime = System.currentTimeMillis();
        // 耗时
        long costTime = endTime - startTime;

        // 构建日志对象
        OperateLog operateLog = new OperateLog();
        operateLog.setOperate_user_id(getCurrentUserId()); // 需要实现 getCurrentUserId 方法
        operateLog.setOperate_time(LocalDateTime.now());
        operateLog.setClass_name(joinPoint.getTarget().getClass().getName());
        operateLog.setMethod_name(joinPoint.getSignature().getName());
        operateLog.setMethod_params(Arrays.toString(joinPoint.getArgs()));
        operateLog.setReturn_value(result.toString());
        operateLog.setCost_time(costTime);

        // 插入日志
        System.out.println("插入日志: " + operateLog);
        operateLogMapper.insert(operateLog);
        return result;
    }

    // 示例方法，获取当前用户ID
    private int getCurrentUserId() {
        // 这里应该根据实际情况从认证信息中获取当前登录用户的ID
        return 1; // 示例返回值
    }
}