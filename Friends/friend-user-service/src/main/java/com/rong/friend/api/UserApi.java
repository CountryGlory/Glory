package com.rong.friend.oauthserver.api;

import com.rong.friend.oauthserver.common.model.Result;
import com.rong.friend.oauthserver.common.model.User;
import com.rong.friend.oauthserver.service.FeignOAuth2Service;
import com.rong.friend.oauthserver.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

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
	public Result<User> getUser() throws  Exception{
			String userId=feignOAuth2Service.getUserid();
			return Result.ok().setData(userService.findUserById(userId));
	}

	@GetMapping("friendByUsername/{username}")
	public Result<User> FriendByUserName(@PathVariable("username") String username) throws Exception{
			return userService.findUserByNameNumber(username);

	}

}
