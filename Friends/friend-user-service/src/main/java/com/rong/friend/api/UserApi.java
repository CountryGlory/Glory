package com.rong.friend.api;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
	private RedisUtil redisUtil;

	/**
	 * 用户登录
	 * 
	 * @param userCode
	 *                      用户账号
	 * @param password
	 *                      密码
	 * @param sessionId
	 *                      session唯一标识，用于验证设备的唯一性
	 * @return
	 */
	@ApiOperation(value = "用户登录")
	@PostMapping("/login")
	public @ResponseBody Result<User> login(@ApiParam(value = "账号", required = true) @RequestParam String username,
			@ApiParam(value = "密码", required = true) @RequestParam String password, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			// String userCode=reques.getParameter("userCode");
			// String password=reques.getParameter("password");
			User user = userService.login(username, password);
			// Cookie [] cookie=reques.getCookies();
			// String sessionId=cookie[0].getValue();
			// System.out.println(sessionId);
			// redisUtil.hmSet(sessionId,"user",user);
			// HttpSession session=request.getSession();
			// System.out.println(session.getId());
			// if(session.getAttribute("user")!=null) {
			// session.removeAttribute("user");
			// }
			// session.setAttribute("user", user);
			redisUtil.set(request.getCookies()[0].getValue(), user.getId());
			logger.info("用户" + username + "登录成功!");
			return Result.ok().setData(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			String error = e.getMessage();
			logger.error(error);
			return Result.failure(500, error);
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
