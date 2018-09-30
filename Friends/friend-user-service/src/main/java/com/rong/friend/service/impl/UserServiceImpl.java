package com.rong.friend.service.impl;

import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.runner.ReactiveWebApplicationContextRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rong.friend.dao.UserMapper;
import com.rong.friend.entity.UserModel;
import com.rong.friend.model.User;
import com.rong.friend.service.UserService;
import com.rong.friend.util.MD5Util;
import com.rong.friend.util.SessionUtil;
import com.rong.friend.util.UUIDUtil;

@Service
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	private UserMapper userMapper;
	 
	@Override
	public User login(String userNumber,String password) throws Exception {
		User user=userMapper.selectBynameNumberLogin(userNumber);
		//userModel.setSessionId(UUIDUtil.UUID64());
		if(user==null) {
			throw new Exception("该账号不存在！");
		}else if(!user.getPassword().equals(MD5Util.generateMD5(password))) {
			user=null;
			throw new Exception("密码错误！");
		}else {
			user.setStatus(1);
			int result=userMapper.updateByPrimaryKey(user);
			if(result!=1) {
				user=null;
				throw new Exception("系统异常");
			}
		}
		//SessionUtil.setSessionId(userModel.getSessionId(),userModel.getId());
		return user;
		
	}
	
	@Override
	public String zjlogin(String nameNumber,String sessionId) throws Exception {
		User user=userMapper.selectBynameNumberLogin(nameNumber);
		if(user==null || !SessionUtil.provingSession(user.getId(), sessionId)) {
			return null;
		}else {
			user.setStatus(1);
			userMapper.updateByPrimaryKeySelective(user);
			SessionUtil.removeSessionId(sessionId);
			String newSessionId=UUIDUtil.UUID64();
			SessionUtil.setSessionId(newSessionId,user.getId());
			return newSessionId;
		}
	}
	
	@Override
	public User register(User user) throws Exception {
		Random random=new Random();
		String nameNumber=random.nextInt(10000)+""+random.nextInt(10000);
		int count=userMapper.selectCountBynameNumber(nameNumber);
		boolean b=true;
		while(b) {
			if(count!=1) {
				user.setNamenumber(nameNumber);
				b=false;
			}else {
				nameNumber=random.nextInt(10000)+""+random.nextInt(10000);
				count=userMapper.selectCountBynameNumber(nameNumber);
			}
		}
		String password=MD5Util.generateMD5(user.getPassword());
		user.setPassword(password);
		int result=userMapper.insertSelective(user);
		if(result==1) {
			return user;
		}
		return null;
	}

	@Override
	public void updateUser(User user) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User findUserById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findUserByNameNumber(String nameNumber) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void binDingEmail(String email, Integer userId) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updatePassword(String password, String newPassword, User user) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void retrievePassword(String namenumber, String email) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional(readOnly=true)
	public boolean verificationEmail(String emial) throws Exception {
		int result=userMapper.selectCountByEmail(emial);
		if(result!=0) {
			return false;
		}else {
			return true;
		}
	}

	@Override
	public Integer verificationSession(String sessionId, String userNumber) throws Exception {
		User user=userMapper.selectBynameNumberLogin(userNumber);
		String userId=SessionUtil.getSessionId(sessionId);
		if(userId.equals(user.getId())) {
			return 1;
		}
		return 0;
	}
	

}
