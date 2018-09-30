package com.rong.friend.api;

import static org.hamcrest.CoreMatchers.nullValue;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rong.friend.model.Chatdialog;
import com.rong.friend.model.User;
import com.rong.friend.service.ChatService;
import com.rong.friend.service.SessionService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 聊天会话API
 * @author 荣
 *
 */
@Api(tags= {"chat"})
@RestController
public class ChatApi {
	
	private static final Logger logger=LoggerFactory.getLogger(ChatApi.class);
	
	@Autowired
	private ChatService chatService;
	
	@Autowired
	private SessionService sessionService;
	
	/**
	 * 聊天会话
	 * @param userCode
	 * @param sessionId
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value="消息首页")
	@GetMapping("/cm")
	public Object chatMain(HttpServletRequest request){
		try {
			String sessionId=request.getCookies()[0].getValue();
			String userId=sessionService.getsession(sessionId);
			Map<String, Object> map=chatService.getChatdialogModelALL(userId);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return "error";
		}
	}
	/**
	 * 标为未读
	 * @param chatdialogId
	 * @param request
	 * @return
	 */
	@ApiOperation(value="标为未读")
	@GetMapping("/ur")
	public Integer signUnread(@ApiParam(value="chatdialogId",required=true) @RequestParam String chatdialogId,HttpServletRequest request){
		try {
			return chatService.markedAsNoRead(chatdialogId)?1:-1;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return -1;
		}
	}
	/**
	 * 标为已读
	 * @param chatdialogId
	 * @param request
	 * @return
	 */
	@ApiOperation(value="标为已读")
	@GetMapping("/sr")
	public Integer signRead(@ApiParam(value="chatdialogId",required=true) @RequestParam String chatdialogId,HttpServletRequest request){
		try {
			return chatService.markedAsRead(chatdialogId)?0:-1;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return -1;
		}
	}
	
	/**
	 * 置顶该聊天
	 * @param chatdialogId
	 * @param request
	 * @return
	 */
	@ApiOperation(value="置顶该聊天")
	@GetMapping("/stc")
	public boolean setTopChat(@ApiParam(value="chatdialogId",required=true)@RequestParam String chatdialogId,HttpServletRequest request) {
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
	 * @param chatdialogId
	 * @param request
	 * @return
	 */
	@ApiOperation(value="删除该聊天")
	@GetMapping("/rc")
	public boolean removeChatdialog(@ApiParam(value="chatdialogId",required=true)@RequestParam String chatdialogId,HttpServletRequest request) {
		try {
			return chatService.removeChatdialog(chatdialogId);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return false;
	}
}
