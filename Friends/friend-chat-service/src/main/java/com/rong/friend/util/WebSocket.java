package com.rong.friend.service.impl;

import java.util.HashMap;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.rong.friend.common.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rong.friend.service.ChatService;

@Component
@ServerEndpoint("/websocket")
public class WebSocket {

	private static final Logger logger = LoggerFactory.getLogger(WebSocket.class);

	@Autowired
	private RedisUtil redisUtil;

	public static HashMap<String, Session> sessionMap = new HashMap<>();

	@OnOpen
	public void onOpen(Session session) {
		String userId = redisUtil.get(session.getId()).toString();
		sessionMap.put(userId, session);
		logger.info(userId + "连接成功！");
	}

	@OnClose
	public void onClose(Session session) {
		String userId = redisUtil.get(session.getId()).toString();
		sessionMap.remove(userId);
		logger.info(userId + "断开连接！");
	}

	@OnMessage
	public void onMessage(String message) {
		SendMess sendMess = new SendMess(message);
		Thread thread = new Thread(sendMess);
		thread.start();
	}

	public class SendMess implements Runnable {
		// @Autowired
		// private ChatRecordMapper chatRecordMapper;
		// @Autowired
		// private ChatdialogMapper ChatdialogMapper;
		@Autowired
		private ChatService chatService;
		private String message;

		public SendMess(String message) {
			this.message = message;
		}

		public SendMess() {
		}

		public void run() {
			try {
				chatService.sendMessage(message);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.error(e.getMessage());
			}
		}

		// public void sendMessage(Session session, String message) {
		// try {
		// session.getBasicRemote().sendText(message);
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }
	}

}
