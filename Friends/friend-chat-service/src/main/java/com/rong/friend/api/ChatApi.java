package com.rong.friend.oauthserver.api;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.rong.friend.oauthserver.common.model.Result;
import com.rong.friend.oauthserver.service.ChatService;
import com.rong.friend.oauthserver.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rong.friend.oauthserver.common.model.ChatRecord;

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

	/**
	 * 聊天会话
	 *
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "消息首页")
	@GetMapping("/cm")
	public Result<Map<String, Object>> chatMain() throws  Exception{
		String userId = userService.getUserid();
		return chatService.getChatdialogModelALL(userId);
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
	public Result<String> signUnread(@ApiParam(value = "chatdialogId", required = true) @RequestParam String chatdialogId,
			HttpServletRequest request) throws  Exception{
		return chatService.markedAsNoRead(chatdialogId);
	}

	/**
	 * 标为已读
	 * 
	 * @param chatdialogId
	 * @return
	 */
	@ApiOperation(value = "标为已读")
	@GetMapping("/sr")
	public Result<String> signRead(@ApiParam(value = "chatdialogId", required = true) @RequestParam String chatdialogId) throws Exception{
		return chatService.markedAsRead(chatdialogId);
	}

	/**
	 * 置顶该聊天
	 * 
	 * @param chatdialogId
	 * @return
	 */
	@ApiOperation(value = "置顶该聊天")
	@GetMapping("/stc")
	public Result<String> setTopChat(@ApiParam(value = "chatdialogId", required = true) @RequestParam String chatdialogId) throws  Exception{
		return chatService.setTopChat(chatdialogId);
	}

	/**
	 * 删除该聊天
	 * 
	 * @param chatdialogId
	 * @return
	 */
	@ApiOperation(value = "删除该聊天")
	@GetMapping("/rc")
	public Result<String> removeChatdialog(
			@ApiParam(value = "chatdialogId", required = true) @RequestParam String chatdialogId) throws  Exception{
		String userId = userService.getUserid();
		return chatService.removeChatdialog(chatdialogId, userId);
	}

	/**
	 * 聊天首页
	 *
	 * @param friendId
	 * @return
	 */
	@ApiOperation(value = "聊天首页")
	@GetMapping("/chatindex")
	public Result<List<Object>> chatindex(@ApiParam(value = "friendId") @RequestParam String friendId)throws  Exception {
		String userId = userService.getUserid();
		return chatService.chatIndex(friendId, userId);
	}

	/**
	 * 查看聊天记录
	 * 
	 * @param friendUserId
	 * @param start
	 * @return
	 */
	@ApiOperation(value = "查看聊天记录")
	@GetMapping("/vtr")
	public Result<List<ChatRecord>> viewTheRecord(@ApiParam(value = "friendUserId") @RequestParam String friendUserId,
			@ApiParam(value = "start") @RequestParam Integer start)throws  Exception {
		String userId=userService.getUserid();
		return chatService.loadChatrRecords(friendUserId, userId, start);
	}
}
