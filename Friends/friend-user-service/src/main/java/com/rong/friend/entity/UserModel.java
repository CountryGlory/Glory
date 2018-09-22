package com.rong.friend.entity;

import java.io.Serializable;

import com.rong.friend.model.User;

public class UserModel  extends User implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5668076105282087835L;

	public UserModel() {}
	public UserModel(User user) {
		super.setAddress(user.getAddress());
		super.setBirthday(user.getBirthday());
		super.setEmail(user.getEmail());
		super.setGesturepwd(user.getGesturepwd());
		super.setHeadportrait(user.getHeadportrait());
		super.setId(user.getId());
		super.setNamenumber(user.getNamenumber());
		super.setNickname(user.getNickname());
		super.setPassword(user.getPassword());
		super.setQq(user.getQq());
		super.setSex(user.getSex());
		super.setStatus(user.getStatus());
		super.setWeixin(user.getWeixin());
	}
	public UserModel(User user,String sessionId){
		super.setAddress(user.getAddress());
		super.setBirthday(user.getBirthday());
		super.setEmail(user.getEmail());
		super.setGesturepwd(user.getGesturepwd());
		super.setHeadportrait(user.getHeadportrait());
		super.setId(user.getId());
		super.setNamenumber(user.getNamenumber());
		super.setNickname(user.getNickname());
		super.setPassword(user.getPassword());
		super.setQq(user.getQq());
		super.setSex(user.getSex());
		super.setStatus(user.getStatus());
		super.setWeixin(user.getWeixin());
		this.sessionId=sessionId;
	}
   
	private String sessionId;

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
}