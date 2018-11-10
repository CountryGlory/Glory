package com.rong.friend.oauthserver.controller;

import java.security.Principal;

import com.rong.friend.model.User;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;

/**
 * 服務之間調用验证用户信息接口
 */
@RestController
public class UserController {

    @RequestMapping("/user")
    public Principal user(Principal user) {
        //System.out.println(user.getName());
        return user;
    }
}