package com.rong.friend.oauthserver.common.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 执行时间注解具体实现类
 */
@Aspect
@Component
public class TrackTimeAspect {
    @Around("@annotation(trackTime)")
    public Object around(ProceedingJoinPoint joinPoint, TrackTime trackTime)throws  Throwable{
        Logger logger= LoggerFactory.getLogger(joinPoint.getSignature().getClass());
        Object result=null;
        long startTime=System.currentTimeMillis();
        result=joinPoint.proceed();
        long timeTaken=System.currentTimeMillis();
        logger.info("---------->Time taken by "+joinPoint+" with param["+trackTime.param()+"] is  "+ timeTaken);
        return result;
    }
}
