package com.aop.utils;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
* @ClassName: HelloAspect
* @Description: web层日志切面
* @author Van
* @date 2017年8月7日 下午12:25:03
 */
@Aspect
@Order(5) //@order(i)注解来标志切面的优先级，i的值越小，优先级越高
@Component
public class HelloAspect {
    
    private Logger logger= Logger.getLogger(getClass());
    
    ThreadLocal<Long> startTime = new ThreadLocal<>();
    
    @Pointcut("execution(public * com.aop.controller..*.*(..))")
    public void hello() {}
    
    @Before("hello()")
    public void doBefore(JoinPoint joinPoint) throws Throwable{
        startTime.set(System.currentTimeMillis());
        
        //接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        
        //记录下请求内容
        logger.info("URL ：" + request.getRequestURI().toString());
        logger.info("HTTP_METHOD ：" + request.getMethod());
        logger.info("IP ： " + request.getRemoteAddr());
        logger.info("CLASS_METHOD ：" +joinPoint.getSignature().getDeclaringTypeName() +"."+joinPoint.getSignature().getName());
        logger.info("ARGS ：" +Arrays.toString(joinPoint.getArgs()));
    }
    
    @AfterReturning(returning = "ret", pointcut = "hello()")
    public void doAfterReturning(Object ret) throws Throwable{
        //处理完请求，返回内存
        logger.info("RESPONSE ： " + ret);
        logger.info("SPEND TIME ：" + (System.currentTimeMillis() - startTime.get()));
    }
}
