package com.zeki.springboot2template.domain._common.utils.trace;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Log4j2
public class loggingAspect {

    @Pointcut("execution(* com..domain..service..*(..))")
    public void serviceMethods() {
    }

    // 해당 포인트컷 전에 실행될 어드바이스 정의
    @Before("serviceMethods()")
    public void logBefore(JoinPoint joinPoint) {
        String methodName = StringUtils.capitalize(joinPoint.getSignature().getName());
        log.info("");
        log.info("==================" + methodName + " Start==================");
    }

    // 해당 포인트컷 후에 실행될 어드바이스 정의
    @After("serviceMethods()")
    public void logAfter(JoinPoint joinPoint) {
        String methodName = StringUtils.capitalize(joinPoint.getSignature().getName());

        log.info("==================" + methodName + " End==================");
        log.info("");
    }

}
