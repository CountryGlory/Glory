package com.rong.friend.oauthserver.common.aop;

import com.rong.friend.oauthserver.common.model.Result;
import org.apache.commons.lang.time.StopWatch;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * AOP
 */
@Aspect
@Component
public class BeforeAspect {
    /**
     * 环绕增强，实现统一异常处理
     * @param jp
     * @return
     */
    @Around(value = "com.rong.friend.oauthserver.common.aop.CommonJoinPointConfig.apiErroeEcexution()")
    public Object aroundError(ProceedingJoinPoint jp){
        Logger logger=LoggerFactory.getLogger(jp.getSignature().getClass());
        StopWatch stopWatch=new StopWatch();
        Object obj=null;
        try{
            stopWatch.start();
            obj=jp.proceed();
            stopWatch.stop();
            logger.info("执行方法：{}，耗时：{}ms(毫秒) ",jp.getSignature(),stopWatch.getTime());
        }catch(Throwable throwable){
            obj=handleException(logger,jp,obj,throwable);
        }
        return obj;
    }

    public Result<?> handleException(Logger logger,ProceedingJoinPoint jp,Object result,Throwable e){
        logger.error("Exception{方法：{}， 参数：{}}", jp.getSignature(), jp.getArgs());
        logger.error(e.getMessage(),e);
        if(result instanceof  Result){
            return (Result<?>)result;
        }else{
            return Result.failure(500,"系统异常，请联系客服！");
        }
    }
}
