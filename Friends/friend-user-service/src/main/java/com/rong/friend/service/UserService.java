package com.rong.friend.oauthserver.service;

import com.rong.friend.oauthserver.common.model.Result;
import com.rong.friend.oauthserver.common.model.User;

/**
 * 用户
 * 
 * @author 荣
 *
 */
public interface UserService {
	/**
	 * 用户登录
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public User login(String nameNumber, String password) throws Exception;

	/**
	 * 注册
	 * 
	 * @param user
	 * @throws Exception
	 */
	public User register(User user) throws Exception;

	/**
	 * 用户编辑资料
	 * 
	 * @param user
	 * @throws Exception
	 */
	public Result updateUser(User user) throws Exception;

	/**
	 * 按id查找用户
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public User findUserById(String id) throws Exception;

	/**
	 * 按账号查找用户
	 * 
	 * @param nameNumber
	 * @return
	 * @throws Exception
	 */
	public Result findUserByNameNumber(String nameNumber) throws Exception;

	/**
	 * 绑定邮箱
	 * 
	 * @param email
	 * @param userId
	 * @throws Exception
	 */
	public void binDingEmail(String email, Integer userId) throws Exception;

	/**
	 * 修改密码
	 * 
	 * @param password
	 * @param newPassword
	 * @param user
	 * @throws Exception
	 */
	public void updatePassword(String password, String newPassword, User user) throws Exception;

	/**
	 * 找回密码
	 * 
	 * @param namenumber
	 * @param email
	 * @throws Exception
	 */
	public void retrievePassword(String namenumber, String email) throws Exception;

	/**
	 * 验证邮箱是否存在
	 * 
	 * @param emial
	 * @throws Exception
	 */
	public boolean verificationEmail(String emial) throws Exception;

}
