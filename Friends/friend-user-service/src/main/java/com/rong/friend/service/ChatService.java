package com.rong.friend.service;

import java.util.List;

import com.rong.friend.entity.ChatDialogModel;

/**
 * 聊天业务逻辑
 * @author 荣
 *
 */
public interface ChatService {
	
	public List<ChatDialogModel> getChatDialogModels(String userCode,String sessionId)throws Exception;
}
