package com.rong.friend.model;

import java.io.Serializable;

/**
 * 聊天会话实体扩展类
 * @author 荣
 *
 */
public class ChatdialogModel extends Chatdialog implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8604758566334453899L;
	
	public ChatdialogModel(Chatdialog chatdialog) {
		super.setFriendsid(chatdialog.getFriendsid());
		super.setId(chatdialog.getId());
		super.setLastdt(chatdialog.getLastdt());
		super.setNewchat(chatdialog.getNewchat());
		super.setSortno(chatdialog.getSortno());
		super.setUnreadchat(chatdialog.getUnreadchat());
		super.setUserid(chatdialog.getUserid());
	}
	
//	private User chatUser;
//
//	public User getChatUser() {
//		return chatUser;
//	}
//
//	public void setChatUser(User chatUser) {
//		this.chatUser = chatUser;
//	}

}
