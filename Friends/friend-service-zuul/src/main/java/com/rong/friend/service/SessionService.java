package com.rong.friend.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value="friend-user-service")
public interface SessionService {

	@RequestMapping(value="/getsession",method = RequestMethod.GET)
	public  String getsession(@RequestParam(name="sessionId") String sessionId);
}
