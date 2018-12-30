package com.rong.friend.oauthserver.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.websocket.Session;

import com.rong.friend.oauthserver.common.util.RedisUtil;
import com.rong.friend.oauthserver.common.util.UUIDUtil;
import com.rong.friend.oauthserver.common.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.rong.friend.oauthserver.common.dao.ChatRecordMapper;
import com.rong.friend.oauthserver.common.dao.ChatdialogMapper;
import com.rong.friend.oauthserver.common.dao.FriendapplyMapper;
import com.rong.friend.oauthserver.common.dao.FriendsMapper;
import com.rong.friend.oauthserver.common.dao.RelatedtomeMapper;
import com.rong.friend.oauthserver.common.dao.UserMapper;
import com.rong.friend.oauthserver.common.model.ChatRecord;
import com.rong.friend.oauthserver.common.model.Chatdialog;
import com.rong.friend.oauthserver.common.model.User;
import com.rong.friend.oauthserver.service.ChatService;
import org.springframework.util.StringUtils;

/**
 * 聊天模块业务接口实现类
 * 
 * @author 荣
 *
 */
@Service
@Transactional
public class ChatServiceImpl implements ChatService {

	@Autowired
	private ChatdialogMapper chatdialogMapper;
	@Autowired
	private FriendapplyMapper friendapplyMapper;
	@Autowired
	private RelatedtomeMapper relatedtomeMapper;
	@Autowired
	private ChatRecordMapper chatRecordMapper;
	@Autowired
	private FriendsMapper friendsMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private RedisUtil redisUtil;

	@Override
	@Transactional(readOnly = true)
	public Result<Map<String, Object>> getChatdialogModelALL(String userId) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Chatdialog> chatdialogs = new ArrayList<>();
		if (!redisUtil.exists("chatdialig_list_userid_" + userId)) {
			chatdialogs = chatdialogMapper.selectChatdialogByUserId(userId);
			int lookChatCount = relatedtomeMapper.selectReadCount(userId);
			int friendChatCount = friendapplyMapper.selectReadCount(userId);
			redisUtil.set("chatdialig_list_userid_" + userId, chatdialogs);
			redisUtil.set("chatdialog_lookChatCount_" + userId, lookChatCount);
			redisUtil.set("chatdialog_friendChatCount_" + userId, friendChatCount);
			map.put("chatdialogs", chatdialogs);
			map.put("lookChatCount", lookChatCount);
			map.put("friendCahtCount", friendChatCount);
		} else {
			chatdialogs = (List<Chatdialog>) redisUtil.get("chatdialig_list_userid_" + userId);
			int lookChatCount = Integer.parseInt(redisUtil.get("chatdialog_lookChatCount_" + userId).toString());
			int friendChatCount = Integer.parseInt(redisUtil.get("chatdialog_friendChatCount_" + userId).toString());
			map.put("chatdialogs", chatdialogs);
			map.put("lookChatCount", lookChatCount);
			map.put("friendCahtCount", friendChatCount);
		}
		return Result.ok().setData(map);
	}

	@Override
	public Result<String> removeChatdialog(String id, String userId) throws Exception {
		int data = chatdialogMapper.deleteByPrimaryKey(id);
		if (data > 0) {

			List<Chatdialog> chatdialogs = (ArrayList<Chatdialog>) redisUtil.get("chatdialig_list_userid_" + userId);
			for (Chatdialog var : chatdialogs) {
				if (var.getId().equals(id)) {
					chatdialogs.remove(var);
				}
			}
			return Result.ok();
		} else {
			return Result.failure(500,"操作失败");
		}
	}

	@Override
	public Result<String> markedAsRead(String id) throws Exception {
		Chatdialog chatdialog = new Chatdialog();
		chatdialog.setId(id);
		chatdialog.setUnreadchat(0);
		int data = chatdialogMapper.updateByPrimaryKeySelective(chatdialog);
		if (data > 0) {
			return Result.ok();
		} else {
			return Result.failure(500,"操作失败");
		}
	}

	@Override
	public Result<String> markedAsNoRead(String id) throws Exception {
		Chatdialog chatdialog = chatdialogMapper.selectByPrimaryKey(id);
		chatdialog.setUnreadchat(chatdialog.getUnreadchat() + 1);
		int data = chatdialogMapper.updateByPrimaryKey(chatdialog);
		if (data > 0) {
			return Result.ok();
		} else {
			return Result.failure(500,"操作失败");
		}
	}

	@Override
	public Result<String> setTopChat(String id) throws Exception {
		int data = chatdialogMapper.updateTopChat(id);
		if (data > 0) {
			return Result.ok();
		} else {
			return Result.failure(500,"操作失败");
		}
	}

	@Override
	public Result<String> addChatdialog(String userId, String friendId, String newChat) throws Exception {
		Chatdialog chatdialog = new Chatdialog();
		chatdialog.setId(UUIDUtil.UUID32());
		chatdialog.setNewchat(newChat);
		chatdialog.setSortno(0);
		chatdialog.setUnreadchat(0);
		chatdialog.setUserid(userId);
		chatdialog.setFriendsid(friendId);
		chatdialog.setLastdt(new Date());
		int data = chatdialogMapper.insertSelective(chatdialog);
		if (data > 0) {
			List<Chatdialog> chatdialogs = (ArrayList<Chatdialog>) redisUtil.get("chatdialig_list_userid_" + userId);
			chatdialogs.add(chatdialog);
			redisUtil.set("chatdialig_list_userid_" + userId, chatdialogs);
			return Result.ok();
		} else {
			return Result.failure(500,"操作失败");
		}
	}

	@Override
	public Result<String> addNewChat(String chatdialogId, String newChat, String userId) throws Exception {
		Chatdialog chatdialog = new Chatdialog();
		chatdialog.setId(chatdialogId);
		chatdialog.setNewchat(newChat);
		chatdialog.setLastdt(new Date());
		Integer data = chatdialogMapper.updateByPrimaryKey(chatdialog);
		if (data > 0) {
			List<Chatdialog> chatdialogs = (ArrayList<Chatdialog>) redisUtil.get("chatdialig_list_userid_" + userId);
			for (Chatdialog var : chatdialogs) {
				if (var.getId().equals(chatdialogId)) {
					var.setNewchat(newChat);
					var.setLastdt(new Date());
				}
			}
			redisUtil.set("chatdialig_list_userid_" + userId, chatdialogs);
			return Result.ok();
		}
		return Result.failure(500,"操作失败");
	}

	@Override
	public Result<List<Object>> chatIndex(String friendId, String userId) throws Exception {
		if(StringUtils.isEmpty(friendId)){
			Result.failure(500,"操作失败");
		}
		List<Object> data = new ArrayList<>();
		List<ChatRecord> chatRecords = new ArrayList<>();
		chatRecords = (List<ChatRecord>) (List) redisUtil.lRange("chat_userId_" + userId + "_friendId_" + friendId, 0,
				-1);
		if (chatRecords.size() == 0) {
			String friendUserId = friendsMapper.selectUserIdByPrimaryKey(friendId);
			User friendUser = userMapper.selectByPrimaryKey(friendUserId);
			chatRecords = chatRecordMapper.selectChatRecordsByUserId(userId, friendUserId, 0, 50);
			data.add(chatRecords);
			data.add(friendUser);
			redisUtil.lPush("chat_userId_" + userId + "_friendId_" + friendId, chatRecords);
			redisUtil.set("chat_friend_" + friendId, friendUser);
		} else {
			Object friendUser = redisUtil.get("chat_friend_" + friendId);
			data.add(chatRecords);
			data.add(friendUser);
		}
		return Result.ok().setData(data);
	}

	@Override
	public Result<List<ChatRecord>> loadChatrRecords(String friendUserId, String userId, Integer start) throws Exception {

		return Result.ok().setData(chatRecordMapper.selectChatRecordsByUserId(userId, friendUserId, start, 50));
	}

	@Override
	public void sendMessage(String message) throws Exception {
		HashMap<String, Session> sessionMap = WebSocket.sessionMap;
		JSON json = JSON.parseArray(message);
		HashMap<String, String> hashMap = json.toJavaObject(HashMap.class);
		String toUserId = hashMap.get("touser");
		String sendUserId = hashMap.get("senduser");
		String msg = hashMap.get("message");
		Session toSession = sessionMap.get(toUserId);
		Session sendSession = sessionMap.get(sendUserId);
		ChatRecord chatRecord = new ChatRecord();
		chatRecord.setId(UUIDUtil.UUID32());
		chatRecord.setMsg(msg);
		chatRecord.setRecUserId(toUserId);
		chatRecord.setSendUserId(sendUserId);
		chatRecord.setCreatedt(new Date());
		// 如果接收方连接成功则发送给接收方客户端并将记录存储数据库状态未已读，否则直接储存数据库状态为未读
		if (toSession != null) {
			toSession.getBasicRemote().sendText(message);
		}
		chatRecord.setStatus(1);

		// 插入聊天记录
		chatRecordMapper.insertChatRecord(chatRecord);
		// 发送方添加最新聊天记录
		String chatdialogId = chatdialogMapper.selectChatdialogId(chatRecord.getSendUserId(),
				chatRecord.getRecUserId());
		this.addNewChat(chatdialogId, msg, chatRecord.getSendUserId());
		// 接收方添加最新聊天记录
		chatdialogId = chatdialogMapper.selectChatdialogId(chatRecord.getRecUserId(), chatRecord.getSendUserId());
		this.addUnreadChatNumber(chatdialogId);
		this.addNewChat(chatdialogId, msg, chatRecord.getRecUserId());

	}

	@Override
	public void addUnreadChatNumber(String chatdialogId) throws Exception {
		chatdialogMapper.addUnreadchatNumber(chatdialogId);
	}

}
