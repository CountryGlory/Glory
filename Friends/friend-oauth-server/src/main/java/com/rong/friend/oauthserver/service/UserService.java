package com.rong.friend.oauthserver.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.websocket.server.PathParam;

import com.rong.friend.model.Result;
import com.rong.friend.model.User;
import com.rong.friend.oauthserver.service.impl.UserServiceImpl;

@FeignClient(name = "friend-user-service", fallback = UserServiceImpl.class)
public interface UserService {
    @GetMapping("friendByUsername/{username}")
    Result<User> findByUsername(@PathVariable("username") String username);
}