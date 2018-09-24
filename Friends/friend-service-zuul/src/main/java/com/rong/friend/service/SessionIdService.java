package com.rong.friend.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value="friend-user-service")
public interface SessionIdService {
	
	@RequestMapping(value="/sessionService",method=RequestMethod.GET)
	Integer verificationSession(@RequestParam(value="sessionId")String sessionId,@RequestParam(value="userCode")String userCode);
}
