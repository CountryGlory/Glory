package com.rong.friend.service;

import java.security.Principal;

import com.rong.friend.common.feign.FeignOauth2RequestInterceptor;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "friend-oauth-server", configuration = FeignOauth2RequestInterceptor.class)
public interface UserService {

    @RequestMapping("/getUserid")
    String getUserid();
}