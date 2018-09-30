package com.rong.friend.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rong.friend.dao.ChatdialogMapper;
import com.rong.friend.dao.FriendapplyMapper;
import com.rong.friend.dao.RelatedtomeMapper;
import com.rong.friend.model.Chatdialog;
import com.rong.friend.model.ChatdialogModel;
import com.rong.friend.service.ChatService;
import com.rong.friend.util.UUIDUtil;

/**
 * 聊天模块业务接口实现类
 * @author 荣
 *
 */
@Service
@Transactional
public class ChatServiceImpl implements ChatService{

	@Autowired
	private ChatdialogMapper chatdialogMapper;
	@Autowired
	private FriendapplyMapper friendapplyMapper;
	@Autowired
	private RelatedtomeMapper relatedtomeMapper;
	
	@Override
	@Transactional(readOnly=true)
	public Map<String, Object> getChatdialogModelALL(String userId) throws Exception {
		Map<String, Object> map=new HashMap<String, Object>();
		List<Chatdialog> chatdialogs=chatdialogMapper.selectChatdialogByUserId(userId);
		int lookChatCount=relatedtomeMapper.selectReadCount(userId);
		int friendChatCount=friendapplyMapper.selectReadCount(userId);
		map.put("chatdialogs", chatdialogs);
		map.put("lookChatCount", lookChatCount);
		map.put("friendCahtCount", friendChatCount);
		return map;
	}
	
	@Override
	public boolean removeChatdialog(String id) throws Exception {
		int data=chatdialogMapper.deleteByPrimaryKey(id);
		if(data>0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean markedAsRead(String id) throws Exception {
		Chatdialog chatdialog=new Chatdialog();
		chatdialog.setId(id);
		chatdialog.setUnreadchat(0);
		int data=chatdialogMapper.updateByPrimaryKeySelective(chatdialog);
		if(data>0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean markedAsNoRead(String id) throws Exception {
		Chatdialog chatdialog=chatdialogMapper.selectByPrimaryKey(id);
		chatdialog.setUnreadchat(chatdialog.getUnreadchat()+1);
		int data=chatdialogMapper.updateByPrimaryKey(chatdialog);
		if(data>0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean setTopChat(String id) throws Exception {
		int data=chatdialogMapper.updateTopChat(id);
		if(data>0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean addChatdialog(String userId, String friendId,String newChat) throws Exception {
		Chatdialog chatdialog=new Chatdialog();
		chatdialog.setId(UUIDUtil.UUID32());
		chatdialog.setNewchat(newChat);
		chatdialog.setSortno(0);
		chatdialog.setUnreadchat(0);
		chatdialog.setUserid(userId);
		chatdialog.setFriendsid(friendId);
		chatdialog.setLastdt(new Date());
		int data=chatdialogMapper.insertSelective(chatdialog);
		if(data>0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean addNewChat(String chatdialogId, String newChat) throws Exception {
		Chatdialog chatdialog=new Chatdialog();
		chatdialog.setId(chatdialogId);
		chatdialog.setNewchat(newChat);
		chatdialog.setLastdt(new Date());
		Integer data=chatdialogMapper.updateByPrimaryKey(chatdialog);
		if(data>0) {
			return true;
		}
		return false;
	}

}
