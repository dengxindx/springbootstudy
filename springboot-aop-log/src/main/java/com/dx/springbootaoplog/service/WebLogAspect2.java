package com.dx.springbootaoplog.service;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 测试切面优先级
 */
@Aspect
@Component
@Order(10)
public class WebLogAspect2 {

    private Logger logger = Logger.getLogger(WebLogAspect2.class);

    @Pointcut("execution(public * com.dx.springbootaoplog.controller..*.*(..))")    // public方法， *返回类型， com.dx.springbootaoplog.controller..*包，包含子包， .*所有方法， (..)所有类型参数
    public void webLog(){}

    @Before("webLog()")
    public void doBefore2() {
        logger.info("before优先级10");
    }


    @AfterReturning(pointcut = "webLog()")
    public void doAfterReturning2(){
        logger.info("after优先级10");
    }
}
