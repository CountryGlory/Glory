package com.rong.friend.oauthserver.service.impl;

import java.util.Random;

import com.rong.friend.oauthserver.common.dao.UserMapper;
import com.rong.friend.oauthserver.common.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rong.friend.oauthserver.common.model.Result;
import com.rong.friend.oauthserver.common.model.User;
import com.rong.friend.oauthserver.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public User login(String userNumber, String password) throws Exception {
		User user = userMapper.selectBynameNumberLogin(userNumber);
		// userModel.setSessionId(UUIDUtil.UUID64());
		if (user == null) {
			throw new Exception("该账号不存在！");
		} else if (!user.getPassword().equals(MD5Util.generateMD5(password))) {
			user = null;
			throw new Exception("密码错误！");
		} else {
			user.setStatus(1);
			int result = userMapper.updateByPrimaryKey(user);
			if (result != 1) {
				user = null;
				throw new Exception("系统异常");
			}
		}
		// SessionUtil.setSessionId(userModel.getSessionId(),userModel.getId());
		return user;

	}

	@Override
	public User register(User user) throws Exception {
		Random random = new Random();
		String nameNumber = random.nextInt(10000) + "" + random.nextInt(10000);
		int count = userMapper.selectCountBynameNumber(nameNumber);
		boolean b = true;
		while (b) {
			if (count != 1) {
				user.setNamenumber(nameNumber);
				b = false;
			} else {
				nameNumber = random.nextInt(10000) + "" + random.nextInt(10000);
				count = userMapper.selectCountBynameNumber(nameNumber);
			}
		}
		String password = MD5Util.generateMD5(user.getPassword());
		user.setPassword(password);
		int result = userMapper.insertSelective(user);
		if (result == 1) {
			return user;
		}
		return null;
	}

	@Override
	public Result updateUser(User user) throws Exception {
		int count = userMapper.updateByPrimaryKeySelective(user);
		if (count < 1) {
			return Result.failure(100, "修改用户失败");
		} else {
			return Result.ok();
		}
	}

	@Override
	public User findUserById(String id) throws Exception {
		// TODO Auto-generated method stub
		return userMapper.selectByPrimaryKey(id);
	}

	@Override
	public Result findUserByNameNumber(String nameNumber) throws Exception {
		// TODO Auto-generated method stub
		User user = userMapper.selectBynameNumber(nameNumber);
		if (user != null) {
			return Result.ok().setData(user);
		} else {
			return Result.failure(100, "用户不存在");
		}
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
	@Transactional(readOnly = true)
	public boolean verificationEmail(String emial) throws Exception {
		int result = userMapper.selectCountByEmail(emial);
		if (result != 0) {
			return false;
		} else {
			return true;
		}
	}

}
