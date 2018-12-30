package com.rong.friend.api;

import java.security.Principal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 服務之間調用验证用户信息接口
 */
@RestController
public class UserApi {

    @RequestMapping("/user")
    public Principal user(Principal user) {
        //System.out.println(user.getName());
        return user;
    }

    @RequestMapping("/getUserid")
    public String getUserno(Principal user){
        String userid=user.getName();
        return userid;
    }
}