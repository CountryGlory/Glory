package com.rong.friend.oauthserver.common.aop;

import org.aspectj.lang.annotation.Pointcut;

public class CommonJoinPointConfig {

    @Pointcut("execution(* com.rong.friend.api..*.*(..))")
    public void apiErroeEcexution(){};
}
