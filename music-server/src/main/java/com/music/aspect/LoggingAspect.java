package com.music.aspect;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

    @Around("execution(* com.music.service..*.*(..))")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        // 方法执行前的逻辑
        log.info("方法 " + joinPoint.getSignature().getName() + " 开始执行");

        try {
            // 调用方法并获取返回值
            Object result = joinPoint.proceed();

            // 方法执行后的逻辑
            long end = System.currentTimeMillis();
            log.info("方法 " + joinPoint.getSignature().getName() + " 执行结束，耗时：" + (end - start) + " ms");

            return result;
        } catch (IllegalArgumentException e) {
            // 处理自定义异常
            log.warn("非法参数异常：" + e.getMessage());
            throw e;
        }
    }
}