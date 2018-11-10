package com.rong.friend.service;

import java.security.Principal;

import com.rong.friend.common.feign.FeignOauth2RequestInterceptor;
import com.rong.friend.model.Result;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "friend-oauth-server", configuration = FeignOauth2RequestInterceptor.class)
public interface UserService {

    @RequestMapping("/user")
    Principal user();
}