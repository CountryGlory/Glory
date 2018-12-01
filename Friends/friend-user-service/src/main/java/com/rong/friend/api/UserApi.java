package com.rong.friend.api;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rong.friend.service.FeignOAuth2Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSONObject;
import com.rong.friend.dao.UserMapper;
import com.rong.friend.entity.UserModel;
import com.rong.friend.model.Result;
import com.rong.friend.model.User;
import com.rong.friend.service.UserService;
import com.rong.friend.util.RedisUtil;
import com.rong.friend.util.SessionUtil;
import com.rong.friend.util.UUIDUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 用户API
 * 
 * @author 荣
 *
 */
@Api(tags = { "user" })
@RestController
public class UserApi {

	private static final Logger logger = LoggerFactory.getLogger(UserApi.class);

	@Autowired
	private UserService userService;

	@Autowired
	private FeignOAuth2Service feignOAuth2Service;

	@ApiOperation(value = "获取用户信息")
	@GetMapping("/my")
	public Result<User> getUser(){
		try{
			String userId=feignOAuth2Service.getUserid();
			return Result.ok().setData(userService.findUserById(userId));
		}catch(Exception e){
			logger.error(e.getMessage());
			return Result.failure(100,"系统错误");
		}
	}

	@GetMapping("friendByUsername/{username}")
	public Result<User> FriendByUserName(@PathVariable("username") String username) {
		try {
			return userService.findUserByNameNumber(username);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Result.failure(100, "系统错误");
		}
	}

}
