package com.rong.friend.api;

import static org.hamcrest.CoreMatchers.nullValue;

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
	private RedisUtil redisUtil;
	
	/**
	 * 用户登录
	 * @param userCode 用户账号
	 * @param password 密码
	 * @param sessionId session唯一标识，用于验证设备的唯一性
	 * @return
	 */
	@ApiOperation(value="用户登录")
	@PostMapping("/login")
	public @ResponseBody Object login(@ApiParam(value="账号",required=true) @RequestParam String userCode
			,@ApiParam(value="密码",required=true) @RequestParam String password,HttpServletRequest request,HttpServletResponse response) {
		try {
			//String userCode=reques.getParameter("userCode");
			//String password=reques.getParameter("password");
			User user=userService.login(userCode, password);
//			Cookie [] cookie=reques.getCookies();
//			String sessionId=cookie[0].getValue();
//			System.out.println(sessionId);
//			redisUtil.hmSet(sessionId,"user",user);
//			HttpSession session=request.getSession();
//			System.out.println(session.getId());
//			if(session.getAttribute("user")!=null) {
//				session.removeAttribute("user");
//			}
//			session.setAttribute("user", user);
			addSession(request, user.getId());
			logger.info("用户"+userCode+"登录成功!");
			return user;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			String error=e.getMessage();
			logger.error(error);
			return error;
		}
	}
	
	@GetMapping("/addsession")
	public void addSession(HttpServletRequest request,@RequestParam Object object) throws Exception {
		Cookie [] cookies=request.getCookies();
		if(cookies.length<=0) {
			throw new Exception("zuul网关无sessionId传入");
		}else {
			String sessionId=cookies[0].getValue();
			redisUtil.remove(sessionId);
			redisUtil.set(sessionId, object);
		}
		
	}
	
	@GetMapping("/getsession")
	public String getSession(@RequestParam(name="sessionId") String sessionId) throws Exception {
		Object object=redisUtil.get(sessionId);
		System.out.println("hello feign");
		if(object==null) {
			return null;
		}
		return object.toString();
	}
	
	@GetMapping("/deletesession")
	public void deleteSession(HttpServletRequest request) throws Exception {
		Cookie [] cookies=request.getCookies();
		if(cookies.length<=0) {
			throw new Exception("zuul网关无sessionId传入");
		}else {
			String sessionId=cookies[0].getValue();
			redisUtil.remove(sessionId);
		}
	}
	
}
