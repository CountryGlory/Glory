package com.rong.friend.service;

import com.rong.friend.common.model.Result;
import com.rong.friend.common.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.rong.friend.service.impl.UserServiceImpl;

@FeignClient(name = "friend-user-service", fallback = UserServiceImpl.class)
public interface UserService {
    @GetMapping("friendByUsername/{username}")
    Result<User> findByUsername(@PathVariable("username") String username)throws  Exception;
}