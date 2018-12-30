//package com.rong.friend.common.aop;
//
//import Result;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
//@Aspect
//@Component
//public class GlobalAspect {
//
//    @Pointcut("execution(public * com.rong.friend.api..*.*(..))")
//    public void pcMethod(){};
//
//    public Result<String> doException(JoinPoint jp,Throwable e){
//        if(e!=null){
//            Logger logger= LoggerFactory.getLogger(jp.getSignature().getClass());
//            logger.error(e.getMessage(),e);
//            return Result.failure(401,"系统错误");
//        }else{
//            return null;
//        }
//    }
//}
