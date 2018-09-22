package com.rong.friend.entity;

import com.rong.friend.model.Chatdialog;

public class ChatDialogModel extends Chatdialog{
	
	private String friendHeader;
	private String friendUserId;
	
	public ChatDialogModel(Chatdialog chatdialog) {
		super.setFriendsid(chatdialog.getFriendsid());
		super.setId(chatdialog.getId());
		super.setLastdt(chatdialog.getLastdt());
		super.setNewchat(chatdialog.getNewchat());
		super.setSortno(chatdialog.getSortno());
		super.setUnreadchat(chatdialog.getUnreadchat());
		super.setUserid(chatdialog.getUserid());
	}

	public String getFriendHeader() {
		return friendHeader;
	}

	public void setFriendHeader(String friendHeader) {
		this.friendHeader = friendHeader;
	}

	public String getFriendUserId() {
		return friendUserId;
	}

	public void setFriendUserId(String friendUserId) {
		this.friendUserId = friendUserId;
	}
	
	
}
