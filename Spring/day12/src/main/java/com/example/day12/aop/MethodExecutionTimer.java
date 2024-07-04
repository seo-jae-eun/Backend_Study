package com.example.day12.aop;

import com.example.day12.test.TestController;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class MethodExecutionTimer {
    private static final Logger logger = LoggerFactory.getLogger(TestController.class); // 수동


    @Pointcut("@annotation(com.example.day12.common.annotation.Timer)")
    private void timerPointcut() {

    }

    @Around("timerPointcut()")
    public Object traceTime(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();

        try {
            stopWatch.start();
            return joinPoint.proceed();
        } finally {
            stopWatch.stop();
            logger.info("{} - 시간 - {}ms ", joinPoint.getSignature().toString(), stopWatch.getTotalTimeMillis());
        }
    }
}