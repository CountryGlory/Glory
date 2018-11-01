package com.rong.friend.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rong.friend.dao.ChatdialogMapper;
import com.rong.friend.model.ChatRecord;
import com.rong.friend.model.Chatdialog;

/**
 * 聊天模块业务逻辑接口
 * 
 * @author 荣
 *
 */
public interface ChatService {

	/**
	 * 查到登录用户的所有聊天会话
	 * 
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getChatdialogModelALL(String userId) throws Exception;

	/**
	 * 移除某个聊天会话
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean removeChatdialog(String id, String userId) throws Exception;

	/**
	 * 标为已读
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean markedAsRead(String id) throws Exception;

	/**
	 * 标为未读
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean markedAsNoRead(String id) throws Exception;

	/**
	 * 置顶该聊天
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean setTopChat(String id) throws Exception;

	/**
	 * 添加一条聊天会话
	 * 
	 * @param userId
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public boolean addChatdialog(String userId, String friendId, String newChat) throws Exception;

	/**
	 * 添加最新聊天会话
	 * 
	 * @param chatdialogId
	 * @param newChat
	 * @return
	 * @throws Exception
	 */
	public boolean addNewChat(String chatdialogId, String newChat, String userId) throws Exception;

	/**
	 * 聊天页
	 * 
	 * @param friendId
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public List<Object> chatIndex(String friendId, String userId) throws Exception;

	/**
	 * 加载聊天记录
	 * 
	 * @param friendUserId
	 * @param userId
	 * @param start
	 * @return
	 * @throws Exception
	 */
	public List<ChatRecord> loadChatrRecords(String friendUserId, String userId, Integer start) throws Exception;

	/**
	 * 增加加未读消息总数
	 */
	public void addUnreadChatNumber(String chatdialogId) throws Exception;

	/**
	 * websocket发送消息
	 * 
	 * @param message
	 */
	public void sendMessage(String message) throws Exception;
}
