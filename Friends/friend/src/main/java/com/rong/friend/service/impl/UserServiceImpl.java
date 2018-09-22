package com.rong.friend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rong.friend.dao.UserMapper;
import com.rong.friend.model.User;
import com.rong.friend.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public User login(User user) throws Exception {
		
		return null;
	}

	@Override
	public String register(User user) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateUser(User user) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User findUserById(String id) throws Exception {
		User user=userMapper.selectByPrimaryKey(id);
		return user;
	}

	@Override
	public User findUserByNameNumber(String nameNumber) throws Exception {
		
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

}
