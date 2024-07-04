package com.example.day12.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect     // 흩어진 관심사(부가 기능)를 하나로 묶은 것(모듈화)
@Component
public class SimpleAop {
    // Pointcut : Advice가 실행될 위치나 시점을 지정,   위치나 시점 = Join Point
    // 위치 지정
    @Pointcut("execution(* com.example.day12.test..*.*(..))")
    private void cut() {    // Advice : 실질적으로 어떤 일을 해야할 지에 대해 정의한 것
                            //          구현하려 했던 실질적인 부가 기능
        System.out.println("컷");
    }

    // 시점 지정, 메소드 실행 전
    @Before("cut()")
    public void before(JoinPoint joinPoint) {
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod(); // 쓰면 안좋은 코드
        System.out.println(joinPoint.getSignature());
        System.out.println(method.getName() + " 메소드 실행 전");
    }

    // 시점 지정, 리턴 후
    @AfterReturning(value = "cut()", returning = "returnObject")
    public void afterReturning(JoinPoint joinPoint, Object returnObject) {
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod(); // 쓰면 안좋은 코드
        System.out.println(joinPoint.getSignature());
        System.out.println(method.getName() + " 메소드 실행 후");
        if(returnObject == null) {
            System.out.println("반환 객체 없음");
        } else {
            System.out.println(returnObject.getClass().getName() + " 객체 반환");
        }
    }
}
