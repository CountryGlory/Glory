package com.rong.friend.service.impl;

import com.rong.friend.service.UserService;
import com.rong.friend.common.model.Result;
import com.rong.friend.common.model.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public Result<User> findByUsername(String username) {
        logger.info("调用{}失败", "friendByUsername");
        return Result.failure(500, "调用friendByUsername接口失败");
    }

}