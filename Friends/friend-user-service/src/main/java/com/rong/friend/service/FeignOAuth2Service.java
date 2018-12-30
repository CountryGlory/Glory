package com.rong.friend.oauthserver.service;

import com.rong.friend.oauthserver.common.feign.FeignOauth2RequestInterceptor;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "friend-oauth-server", configuration = FeignOauth2RequestInterceptor.class)
public interface FeignOAuth2Service {

    @RequestMapping("/getUserid")
    String getUserid();
}