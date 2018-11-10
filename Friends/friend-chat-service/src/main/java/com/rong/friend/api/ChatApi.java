package com.rong.friend.api;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.druid.util.StringUtils;
import com.rong.friend.common.util.WebContextUtil;
import com.rong.friend.model.ChatRecord;
import com.rong.friend.model.User;
import com.rong.friend.service.ChatService;
import com.rong.friend.service.UserService;
import com.rong.friend.util.RedisUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 聊天会话API
 * 
 * @author 荣
 *
 */
@Api(tags = { "chat" })
@RestController
public class ChatApi {

	private static final Logger logger = LoggerFactory.getLogger(ChatApi.class);

	@Autowired
	private ChatService chatService;

	@Autowired
	private UserService userService;

	@Autowired
	private RedisUtil redisUtil;

	@Autowired
	private HttpServletRequest request;

	/**
	 * 聊天会话
	 * 
	 * @param userCode
	 * @param sessionId
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "消息首页")
	@GetMapping("/cm")
	public Object chatMain() {
		try {
			long t1 = System.currentTimeMillis();
			logger.info("时间一：" + t1 + "ms");
			Principal newuser = userService.user();
			String userId = newuser.getName();
			long t2 = System.currentTimeMillis();
			logger.info("时间二：" + t2 + "ms");
			// 可能出错了
			Map<String, Object> map = chatService.getChatdialogModelALL(userId);
			long t3 = System.currentTimeMillis();
			logger.info("时间三：" + t3 + "ms");
			logger.info("业务逻辑用时：" + (t3 - t2) + "ms");
			logger.info("总共用时：" + (t3 - t1) + "ms");
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return "error";
		}
	}

	/**
	 * 标为未读
	 * 
	 * @param chatdialogId
	 * @param request
	 * @return
	 */
	@ApiOperation(value = "标为未读")
	@GetMapping("/ur")
	public Integer signUnread(@ApiParam(value = "chatdialogId", required = true) @RequestParam String chatdialogId,
			HttpServletRequest request) {
		try {
			return chatService.markedAsNoRead(chatdialogId) ? 1 : -1;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return -1;
		}
	}

	/**
	 * 标为已读
	 * 
	 * @param chatdialogId
	 * @param request
	 * @return
	 */
	@ApiOperation(value = "标为已读")
	@GetMapping("/sr")
	public Integer signRead(@ApiParam(value = "chatdialogId", required = true) @RequestParam String chatdialogId,
			HttpServletRequest request) {
		try {
			return chatService.markedAsRead(chatdialogId) ? 0 : -1;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return -1;
		}
	}

	/**
	 * 置顶该聊天
	 * 
	 * @param chatdialogId
	 * @param request
	 * @return
	 */
	@ApiOperation(value = "置顶该聊天")
	@GetMapping("/stc")
	public boolean setTopChat(@ApiParam(value = "chatdialogId", required = true) @RequestParam String chatdialogId,
			HttpServletRequest request) {
		try {
			return chatService.setTopChat(chatdialogId);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return false;
	}

	/**
	 * 删除该聊天
	 * 
	 * @param chatdialogId
	 * @param request
	 * @return
	 */
	@ApiOperation(value = "删除该聊天")
	@GetMapping("/rc")
	public boolean removeChatdialog(
			@ApiParam(value = "chatdialogId", required = true) @RequestParam String chatdialogId,
			HttpServletRequest request) {
		try {
			String sessionId = request.getCookies()[0].getValue();
			String userId = redisUtil.get(sessionId).toString();
			return chatService.removeChatdialog(chatdialogId, userId);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return false;
	}

	/**
	 * 聊天首页
	 * 
	 * @param chatId
	 * @param friendId
	 * @return
	 */
	@ApiOperation(value = "聊天首页")
	@GetMapping("/chatindex")
	public List<Object> chatindex(@ApiParam(value = "friendId") @RequestParam String friendId,
			HttpServletRequest request) {
		try {
			long t1 = System.currentTimeMillis();
			logger.info("时间一：" + t1 + "ms");
			String sessionId = request.getCookies()[0].getValue();
			String userId = redisUtil.get(sessionId).toString();
			long t2 = System.currentTimeMillis();
			logger.info("时间二：" + t2 + "ms");
			List<Object> data = chatService.chatIndex(friendId, userId);
			long t3 = System.currentTimeMillis();
			logger.info("时间三：" + t3 + "ms");
			logger.info("业务逻辑用时：" + (t3 - t2) + "ms");
			logger.info("总共用时：" + (t3 - t1) + "ms");
			return data;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}

	/**
	 * 查看聊天记录
	 * 
	 * @param friendUserId
	 * @param userId
	 * @param start
	 * @return
	 */
	@ApiOperation(value = "查看聊天记录")
	@GetMapping("/vtr")
	public List<ChatRecord> viewTheRecord(@ApiParam(value = "friendUserId") @RequestParam String friendUserId,
			@ApiParam(value = "userId") @RequestParam String userId,
			@ApiParam(value = "start") @RequestParam Integer start) {
		try {
			return chatService.loadChatrRecords(friendUserId, userId, start);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return null;
		}
	}
}
