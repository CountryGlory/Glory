package com.rong.friend.api;

import static org.hamcrest.CoreMatchers.nullValue;

import javax.servlet.http.HttpServletRequest;

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

import com.alibaba.fastjson.JSONObject;
import com.rong.friend.dao.UserMapper;
import com.rong.friend.entity.UserModel;
import com.rong.friend.model.User;
import com.rong.friend.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 用户API
 * @author 荣
 *
 */
@Api(tags= {"user"})
@RestController
public class UserApi {

	private static final Logger logger=LoggerFactory.getLogger(UserApi.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RedisTemplate<String, String> redistemplate;
	
	/**
	 * 用户登录
	 * @param userCode 用户账号
	 * @param password 密码
	 * @param sessionId session唯一标识，用于验证设备的唯一性
	 * @return
	 */
	@ApiOperation(value="用户登录")
	@PostMapping("/login")
	public @ResponseBody String login(@ApiParam(value="账号",required=true) @RequestParam String userCode
			,@ApiParam(value="密码",required=true) @RequestParam String password) {
		try {
			UserModel user=userService.login(userCode, password);
			JSONObject jsonObject=new JSONObject();
			String data=jsonObject.toJSON(user).toString();
			logger.info("用户"+userCode+"登录成功!");
			return data;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			String error=e.getMessage();
			logger.error(error);
			return error;
		}
	}
	
	/**
	 * 自动登录
	 * @param userCode
	 * @param status
	 * @return
	 */
	@ApiOperation(value="自动登录")
	@GetMapping("/zidonglogin")
	public boolean zidongLogin(@ApiParam(value="账号",required=true) @RequestParam String userCode) {
		try {
			boolean result=userService.zjlogin(userCode);
			logger.info("自动登录成功！");
			return result;
		} catch (Exception e) {
			logger.error("自动登录失败:"+e.getMessage());
			return false;
		}
	}
	
	/**
	 * 聊天会话
	 * @param userCode
	 * @param sessionId
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value="消息首页")
	@GetMapping("/chatMain")
	public Object chatMain(@ApiParam(value="账号",required=true) @RequestParam String userCode)throws Exception{
		try {
			
		} catch (Exception e) {
			
		}
		return null;
	}
	
	@GetMapping("/sessionService")
	public Integer sessionService(@RequestParam String sessionId,@RequestParam String userCode) {
		Integer result;
		try {
			result = userService.verificationSession(sessionId, userCode);
			return result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
}
